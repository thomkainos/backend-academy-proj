package org.example.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.example.exception.AuthDaoException;
import org.example.exception.InvalidCredentialsException;
import org.example.models.LoginRequest;
import org.example.services.AuthService;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Kainos Auth API")
@Path("/api/auth") @AllArgsConstructor
public class AuthController {
    AuthService authService;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid final LoginRequest loginRequest) {
        try {
            return Response
                    .ok()
                    .entity(authService.login(loginRequest))
                    .build();
        } catch (AuthDaoException e) {
            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidCredentialsException e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
        }
    }
}
