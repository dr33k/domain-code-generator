package com.seven.codegen;

import java.util.Map;

public class Constants {
    public static final String CONTROLLER_PATH = "controller.drk";
    public static final String ENTITY_PATH = "entity.drk";
    public static final String DTO_PATH = "dto.drk";
    public static final String REPO_PATH = "repo.drk";
    public static final String SEARCH_SPEC_PATH = "searchspec.drk";
    public static final String SERVICE_PATH = "service.drk";

    public static final Map<String, String> drkFiles = Map.of(
            "Controller", CONTROLLER_PATH,
            "Entity", ENTITY_PATH,
            "DTO", DTO_PATH,
            "Repository", REPO_PATH,
            "SearchSpecification", SEARCH_SPEC_PATH,
            "Service", SERVICE_PATH
    );

    //Placeholders
    public static final String PACKAGE_NAME = "package";
    public static final String SERVICE_NAME_UPP = "service_name_upp";
    public static final String DOMAIN_NAME_UPP = "domain_upp";
    public static final String DOMAIN_NAME_LOW = "domain_low";
}
