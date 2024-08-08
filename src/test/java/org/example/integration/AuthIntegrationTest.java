package org.example.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

import static org.example.utils.TestUtils.decodeBase64TokenSectionAndMapToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthIntegrationTest {
    private final String apiUrl;

    private static final DropwizardAppExtension<JobRoleManagerConfiguration> APP =
            new DropwizardAppExtension<>(JobRoleManagerApplication.class);

    public AuthIntegrationTest() {
        this.apiUrl = getApiUrl();
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

        JsonNode headerNode = decodeBase64TokenSectionAndMapToJson(tokenSections[0]);
        JsonNode payloadNode = decodeBase64TokenSectionAndMapToJson(tokenSections[1]);

        assertEquals("HS256", headerNode.get("alg").asText());
        assertNotNull(payloadNode.get("iss").asText());
        assertNotNull(payloadNode.get("exp").asText());
        assertEquals(payloadNode.get("Role").asText(), "1");
        assertEquals(payloadNode.get("sub").asText(), "user1");
        assertEquals(payloadNode.get("iss").asText(), "Kainos Job Role Manager");
    }

    // FIX ME: Remove disabled annotation when API is deployed so test can run w/ correct URL
    @Disabled
    void login_shouldReturnUnauthorizedStatusCode_whenRequestContainsInvalidCredentials() {
        Client client = APP.client();
        LoginRequest invalidLoginRequest = new LoginRequest("invalid", "invalid");

        Response response = client
                .target(this.apiUrl + "auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(invalidLoginRequest, MediaType.APPLICATION_JSON), Response.class);

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    // FIX ME: Remove disabled annotation when API is deployed so test can run w/ correct URL
    @Disabled
    void login_shouldReturnUnprocessableEntityCode_whenRequestContainsInvalidFormat() {
        Client client = APP.client();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode invalidJson = mapper.createObjectNode();

        invalidJson.put("username", "user1");

        Response response = client
                .target(this.apiUrl + "auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(invalidJson, MediaType.APPLICATION_JSON), Response.class);

        assertEquals(422, response.getStatus());
    }

    // FIX ME: Remove disabled annotation when API is deployed so test can run w/ correct URL
    @Disabled
    void login_shouldReturnBadRequestCode_whenRequestContainsInvalidLoginRequestFormat() {
        Client client = APP.client();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode invalidJson = mapper.createObjectNode();
        ObjectNode randomJson = mapper.createObjectNode();

        randomJson.put("random", "item");
        invalidJson.set("username", randomJson);

        Response response = client
                .target(this.apiUrl + "auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(invalidJson, MediaType.APPLICATION_JSON), Response.class);

        assertEquals(400, response.getStatus());
    }

    // FIX ME: Remove disabled annotation when API is deployed so test can run w/ correct URL
    @Disabled
    void login_shouldReturnBadRequestCode_whenRequestContainsInvalidRequest() {
        Client client = APP.client();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode invalidJson = mapper.createObjectNode();
        ObjectNode randomJson = mapper.createObjectNode();

        randomJson.put("random", "item");

        Response response = client
                .target(this.apiUrl + "auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(invalidJson, MediaType.APPLICATION_JSON), Response.class);

        assertEquals(400, response.getStatus());
    }

    private String getApiUrl() {
        return System.getenv("API_URL") != null && !System.getenv().isEmpty() ?
                System.getenv("API_URL") :
                "http://localhost:8080/api/";
    }
}
