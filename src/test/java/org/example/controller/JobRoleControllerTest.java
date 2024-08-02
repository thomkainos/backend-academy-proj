package org.example.controller;

import org.example.controllers.JobRoleController;
import org.example.exception.JobRoleDaoException;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JobRoleControllerTest {
    JobRoleService jobRoleService = mock(JobRoleService.class);

    private final JobRoleController jobRoleController = new JobRoleController(jobRoleService);

    @Test
    void getListOpenJobRoles_shouldReturnListOfJobRoleResponseInResponse_whenServiceReturnsListOfJobRoleResponse()
            throws JobRoleDaoException {
        List<JobRoleResponse> jobRoleResponses = new ArrayList<>();

        when(jobRoleService.getAllOpenJobRoles()).thenReturn(jobRoleResponses);

        Response response = jobRoleController.getListOpenJobRoles();

        assertEquals(200, response.getStatus());
        assertEquals(jobRoleResponses, response.getEntity());
    }

    @Test
    void getListOpenJobRoles_shouldReturn500StatusCode_whenServiceThrowsJobRoleDaoException()
            throws JobRoleDaoException {
        when(jobRoleService.getAllOpenJobRoles()).thenThrow(JobRoleDaoException.class);

        Response response = jobRoleController.getListOpenJobRoles();

        assertEquals(500, response.getStatus());
    }
}
