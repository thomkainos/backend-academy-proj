package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.Jwts;
import org.example.controllers.AuthController;
import org.example.controllers.JobRoleController;
import org.example.daos.MySqlAuthDao;
import org.example.services.AuthService;
import org.example.utils.DatabaseConnector;
import org.example.daos.MySqIJobRoleDao;
import org.example.services.JobRoleService;

import java.security.Key;

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
        Key jwtKey = Jwts.SIG.HS256.key().build();

        environment.jersey().register(
                new JobRoleController(
                        new JobRoleService(
                                new MySqIJobRoleDao(databaseConnector))));

        environment.jersey().register(
                new AuthController(
                        new AuthService(
                                new MySqlAuthDao(databaseConnector), jwtKey)));
    }
}
