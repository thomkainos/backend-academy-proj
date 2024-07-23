package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exception.DatabaseConnectionException;
import org.example.services.JobRoleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.sql.SQLException;

@Api("Kainos Job Role Manager API")
@Path("/api/job-roles")
public class JobRoleController {
    JobRoleService jobRoleService;

    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListOpenJobRoles() throws SQLException {
        try {
            return Response.ok().entity(jobRoleService.getAllOpenJobRoles()).build();
        } catch (SQLException | DatabaseConnectionException e) {
            return Response.serverError().build();
        }
    }
}
