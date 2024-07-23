package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class JobRoleManagerConfiguration extends Configuration {
    @Valid
    @NotNull
    private final SwaggerBundleConfiguration swagger =
            new SwaggerBundleConfiguration();
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger() {
        swagger.setResourcePackage("org.example.controllers");
        String[] schemes = {"http", "https"};
        swagger.setSchemes(schemes);
        return swagger;
    }
}
