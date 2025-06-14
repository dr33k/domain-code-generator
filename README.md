## Domain Code Generator
This utility was born out of my laziness towards the mundane process of writing similar business logic for REST Domain objects.
So I automated it.

It Generates Spring Boot tailored Java Code for a particular Domain object at a time.

Great tool for laying off Junior Devs and Interns at a company.

### Build:
* Navigate to project folder
* Using maven, clean and install

### Run
`java -jar target\domain_scaffolder-1.0-SNAPSHOT.jar com.package.name MyServiceName DomainName`