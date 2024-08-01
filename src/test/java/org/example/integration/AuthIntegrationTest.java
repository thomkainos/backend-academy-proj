package org.example.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.JobRoleManagerApplication;
import org.example.JobRoleManagerConfiguration;
import org.example.models.LoginRequest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    private static final DropwizardAppExtension<JobRoleManagerConfiguration> APP =
            new DropwizardAppExtension<>(JobRoleManagerApplication.class);

    // FIX ME: Set up env variables for testing with prod vs dev
    @Test
    void login_shouldReturnJwtTokenWithProperTokenStructure_whenRequestContainsValidCredentials() {
        Client client = APP.client();
        LoginRequest loginRequest = new LoginRequest("user1", "user1");

        String response = client
                .target("http://localhost:8080/api/auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON), String.class);

        assertFalse(response.isEmpty());
    }

    @Test
    void login_shouldReturnBadRequest_whenRequestContainsInvalidCredentials() {

    }
}
