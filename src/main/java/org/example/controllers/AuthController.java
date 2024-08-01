package org.example.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.example.models.LoginRequest;
import org.example.services.AuthService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Auth API")
@Path("/api/auth") @AllArgsConstructor
public class AuthController {
    AuthService authService;

//    @POST
//    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response login(LoginRequest loginRequest) {
////        try {
////            // Return Jwt if credentials are valid
////        }
//
//        // Return 500 if AuthDaoException thrown
//
//        // Return 401 if InvalidCredentailsExcepthon thrown
//    }
}
