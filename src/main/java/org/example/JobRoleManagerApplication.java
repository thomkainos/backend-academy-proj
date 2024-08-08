package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.security.Keys;
import org.example.controllers.AuthController;
import org.example.controllers.JobRoleController;
import org.example.daos.MySqIJobRoleDao;
import org.example.daos.MySqlAuthDao;
import org.example.services.AuthService;
import org.example.services.JobRoleService;
import org.example.utils.DatabaseConnector;

import java.security.Key;
import java.util.Base64;

public class JobRoleManagerApplication extends
        Application<JobRoleManagerConfiguration> {
    public static void main(final String[] args) throws Exception {
        new JobRoleManagerApplication().run(args);
    }

    @Override
    public void initialize(
            final Bootstrap<JobRoleManagerConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    final JobRoleManagerConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }
    @Override
    public void run(final JobRoleManagerConfiguration configuration,
                    final Environment environment) {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        Key jwtKey = getSigningKey();
        if (jwtKey == null) {
            throw new RuntimeException(
                    "Please specify a JWT_KEY environment variable");
        }

        environment.jersey().register(
                new JobRoleController(
                        new JobRoleService(
                                new MySqIJobRoleDao(databaseConnector))));

        environment.jersey().register(
                new AuthController(
                        new AuthService(
                                new MySqlAuthDao(databaseConnector), jwtKey)));
    }

    private Key getSigningKey() {
        String signingKeyBase64 = System.getenv("JWT_KEY");

        if (signingKeyBase64 == null || signingKeyBase64.isEmpty()) {
            return null;
        }

        byte[] keyInBytes = Base64.getDecoder().decode(signingKeyBase64);

        return Keys.hmacShaKeyFor(keyInBytes);
    }
}
