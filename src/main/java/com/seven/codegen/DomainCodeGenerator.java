package com.seven.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DomainCodeGenerator {
    public static void main(String[] args) throws IOException {
        assert args.length == 3 : "Please provide 3 arguments only: " +
                "\n* The package name" +
                "\n* The capitalized name of this service eg MyService" +
                "\n* The Capitalized name of this domain eg Book";

        String domainNameLow = args[2].toLowerCase();
        generate(args[0], args[1], args[2], domainNameLow);
    }

    private static void generate(String packageName, String serviceNameUpp, String domainNameUpp, String domainNameLow) throws IOException {
        Path outputPath = Paths.get(System.getProperty("user.home"), "Desktop/generated_code", domainNameLow);
        Files.list(outputPath).forEach(
                path -> {
                    if(Files.isRegularFile(path)){
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        Files.createDirectories(outputPath);

        for (Map.Entry<String, String> drkFile : Constants.drkFiles.entrySet()) {
            //Load script and replace placeholders
            String placeholderScript = loadScript(drkFile.getValue());
            String javaFileString = replacePlaceholders(placeholderScript,
                    Map.of(
                            Constants.PACKAGE_NAME, packageName,
                            Constants.SERVICE_NAME_UPP, serviceNameUpp,
                            Constants.DOMAIN_NAME_UPP, domainNameUpp,
                            Constants.DOMAIN_NAME_LOW, domainNameLow
                    ));

            //Create file
            File javaFile =  Files.createFile(outputPath.resolve(String.format("%s%s.java", domainNameUpp, drkFile.getKey()))).toFile();

            //Write into created file
            try(FileOutputStream fos = new FileOutputStream(javaFile)){
                byte[] bytes = javaFileString.getBytes(StandardCharsets.UTF_8);
                fos.write(bytes);
            }
        }
    }


    private static String loadScript(String path) throws IOException {
        try (InputStream is = DomainCodeGenerator.class.getClassLoader().getResourceAsStream(path)) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private static String replacePlaceholders(String placeholderString, Map<String, String> placeholderMap) {
        for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {
            placeholderString = placeholderString.replace(String.format("${%s}", entry.getKey()), entry.getValue());
        }
        return placeholderString;
    }

}
