package org.example.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.JobRoleManagerApplication;
import org.example.JobRoleManagerConfiguration;
import org.example.models.LoginRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    private final String apiUrl;

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
    // FIX ME: Remove disabled annotation when API is deployed so test can run w/ correct URL
    @Disabled
    void login_shouldReturnJwtTokenWithProperTokenStructure_whenRequestContainsValidCredentials()
            throws JsonProcessingException {
        Client client = APP.client();
        LoginRequest loginRequest = new LoginRequest("user1", "user1");

        String response = client
                .target(this.apiUrl + "auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON), String.class);

        assertFalse(response.isEmpty());

        String splitByPeriodRegex = "\\.";
        String[] tokenSections = response.split(splitByPeriodRegex);

        Base64.Decoder decoder = Base64.getDecoder();
        String headerAsStr = new String(decoder.decode(tokenSections[0]));
        String payloadAsStr = new String(decoder.decode(tokenSections[1]));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode headerNode = objectMapper.readTree(headerAsStr);
        JsonNode payloadNode = objectMapper.readTree(payloadAsStr);

        assertEquals("HS256", headerNode.get("alg").asText());
        assertNotNull(payloadNode.get("iss").asText());
        assertNotNull(payloadNode.get("exp").asText());
        assertEquals(payloadNode.get("Role").asText(), "1");
        assertEquals(payloadNode.get("sub").asText(), "user1");
        assertEquals(payloadNode.get("iss").asText(), "Kainos Job Role Manager");
    }

    // FIX ME: Remove disabled annotation when API is deployed so test can run w/ correct URL
    @Disabled
    void login_shouldReturnBadRequestCode_whenRequestContainsInvalidCredentials() {
        Client client = APP.client();
        LoginRequest invalidLoginRequest = new LoginRequest("invalid", "invalid");

        Response response = client
                .target(this.apiUrl + "auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(invalidLoginRequest, MediaType.APPLICATION_JSON), Response.class);

        assertEquals(400, response.getStatus());
    }
}
