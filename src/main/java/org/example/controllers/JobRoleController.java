package org.example.controllers;

import io.swagger.annotations.Api;
import org.example.exception.JobRoleDaoException;
import org.example.models.JobRoleDetailsResponse;
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
    public Response getJobRoleById(@PathParam("id") final String id) {
        try {
            int parsedId;
            try {
                parsedId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            JobRoleDetailsResponse response =
                    jobRoleService.getJobRoleById(parsedId);
            if (response.getRoleId() == 0) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.ok().entity(response).build();
            }
        } catch (JobRoleDaoException e) {
            return Response.serverError().build();
        }
    }
}
