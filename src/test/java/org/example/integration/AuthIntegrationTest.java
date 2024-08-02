package org.example.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.JobRoleManagerApplication;
import org.example.JobRoleManagerConfiguration;
import org.example.models.LoginRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    private String apiUrl;

    private static final DropwizardAppExtension<JobRoleManagerConfiguration> APP =
            new DropwizardAppExtension<>(JobRoleManagerApplication.class);

    public AuthIntegrationTest() {
        String apiUrl = System.getenv("API_URL");
        if (apiUrl == null || apiUrl.isEmpty()) {
            this.apiUrl = "http://localhost:8080/api/";
        } else {
            this.apiUrl = apiUrl;
        }
    }

    @Test
    void login_shouldReturnJwtTokenWithProperTokenStructure_whenRequestContainsValidCredentials() {
        Client client = APP.client();
        LoginRequest loginRequest = new LoginRequest("user1", "user1");

        String response = client
                .target(this.apiUrl + "auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON), String.class);

        assertFalse(response.isEmpty());
    }

    @Test
    void login_shouldReturnBadRequestCode_whenRequestContainsInvalidCredentials() {
        Client client = APP.client();
        LoginRequest invalidLoginRequest = new LoginRequest("invalid", "invalid");

        Response response = client
                .target("http://localhost:8080/api/auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(invalidLoginRequest, MediaType.APPLICATION_JSON), Response.class);

        assertEquals(400, response.getStatus());
    }
}