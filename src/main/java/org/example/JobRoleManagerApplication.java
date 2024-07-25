package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.example.controllers.JobRoleController;
import org.example.utils.DatabaseConnector;
import org.example.daos.MySqlJobRoleDao;
import org.example.services.JobRoleService;

public class JobRoleManagerApplication extends
        Application<JobRoleManagerConfiguration> {
    public static void main(final String[] args) throws Exception {
        new JobRoleManagerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Test";
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
        environment.jersey()
                // Add mechanism to pass in appropriate Dao class
                .register(new JobRoleController(
                        new JobRoleService(
                                new MySqlJobRoleDao(databaseConnector))));
    }

}
