package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import org.example.controllers.AuthController;
import org.example.controllers.JobRoleController;
import org.example.daos.MySqIJobRoleDao;
import org.example.daos.MySqlAuthDao;
import org.example.services.AuthService;
import org.example.services.JobRoleService;
import org.example.utils.DatabaseConnector;

public class JobRoleManagerApplication extends
		Application<JobRoleManagerConfiguration> {
	public static void main(final String[] args) throws Exception {
		new JobRoleManagerApplication().run(args);
	}

	@Override
	public void initialize(final Bootstrap<JobRoleManagerConfiguration> bootstrap) {
		bootstrap.addBundle(new SwaggerBundle<>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
					final JobRoleManagerConfiguration configuration) {
				return configuration.getSwagger();
			}
		});
	}

	@Override
	public void run(final JobRoleManagerConfiguration configuration, final Environment environment) {
		DatabaseConnector databaseConnector = new DatabaseConnector();

		var jobRoleService = new JobRoleService(new MySqIJobRoleDao(databaseConnector));
		environment.jersey().register(new JobRoleController(jobRoleService));

		var authService = new AuthService(new MySqlAuthDao(databaseConnector), getSigningKey());
		environment.jersey().register(new AuthController(authService));
	}

	private Key getSigningKey() {
		String signingKeyBase64;
		try {
			signingKeyBase64 = System.getenv().get("JWT_KEY");
		} catch (NullPointerException e) {
			throw new RuntimeException("Please specify a JWT_KEY environment variable");
		}

		byte[] keyInBytes = Base64.getDecoder().decode(signingKeyBase64);

		return Keys.hmacShaKeyFor(keyInBytes);
	}
}
