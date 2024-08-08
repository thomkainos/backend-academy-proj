package org.example.integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.JobRoleManagerApplication;
import org.example.JobRoleManagerConfiguration;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleManagerIntegrationTest {
    private static final DropwizardAppExtension<JobRoleManagerConfiguration> APP =
            new DropwizardAppExtension<>(JobRoleManagerApplication.class);

    // Server isn't hosted yet, cannot make actual request to live endpoint
    @Disabled
    void getListOpenJobRoles_shouldReturnListOfJobRoles() {
        Client client = APP.client();

        List<JobRoleResponse> response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }

    @Disabled
    void getJobRoleById_shouldReturnJobRole() {
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles/1")
                .request()
                .get();

        Assertions.assertEquals(1, response.readEntity(JobRole.class).getRoleId());
    }

    @Disabled
    void getJobRoleById_shouldReturn404WhenIdDoesntExist() {
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles/12345678")
                .request()
                .get();

        Assertions.assertEquals(404, response.getStatus());
    }

    @Disabled
    void getJobRoleById_shouldReturn400WhenIdIsNotValid() {
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles/invalidId")
                .request()
                .get();

        Assertions.assertEquals(400, response.getStatus());
    }
}
