package org.example.controllers;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.example.exception.JobRoleDaoException;
import org.example.services.JobRoleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Kainos Job Role Manager API")
@Path("/api/job-roles")
public class JobRoleController {
    private JobRoleService jobRoleService;

    public JobRoleController(final JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListOpenJobRoles() {
        try {
            return Response
                    .ok()
                    .entity(jobRoleService.getAllOpenJobRoles())
                    .build();
        } catch (JobRoleDaoException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoleById(@PathParam("id") final int id) {
        try {
            return Response.status(HttpStatus.OK_200)
                    .entity(jobRoleService.getJobRoleById(id)).build();
        } catch (JobRoleDaoException e) {
            return Response.serverError().build();
        }
    }
}
