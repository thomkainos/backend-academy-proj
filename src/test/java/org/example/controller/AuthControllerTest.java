package org.example.controller;

import org.example.controllers.AuthController;
import org.example.exception.AuthDaoException;
import org.example.exception.InvalidCredentialsException;
import org.example.models.LoginRequest;
import org.example.models.LoginResponse;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthControllerTest {
    AuthService authService = mock(AuthService.class);
    AuthController authController = new AuthController(authService);

    String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                    + "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkJhZWxkdW5nIFVzZXIiLCJpYXQiOjE1MTYyMzkwMjJ9."
                    + "qH7Zj_m3kY69kxhaQXTa-ivIpytKXXjZc1ZSmapZnGE";

    LoginRequest loginRequest = new LoginRequest("user1", "user1");

    @Test
    public void login_shouldReturnLoginResponseObject_whenServiceReturnsJwtToken()
            throws InvalidCredentialsException, AuthDaoException {
        LoginResponse expLoginResponse = new LoginResponse(jwtToken);
        when(authService.login(loginRequest)).thenReturn(expLoginResponse);

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        LoginResponse actualLoginResponse = (LoginResponse) response.getEntity();
        assertEquals(jwtToken, actualLoginResponse.getToken());
    }

    @Test
    public void login_shouldReturnUnauthorizedStatus_whenServiceThrowsInvalidCredentialsException()
            throws InvalidCredentialsException, AuthDaoException {
        when(authService.login(loginRequest)).thenThrow(
                InvalidCredentialsException.class);

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void login_shouldReturn500Status_whenServiceThrowsAuthDaoException()
            throws InvalidCredentialsException, AuthDaoException {
        when(authService.login(loginRequest)).thenThrow(
                AuthDaoException.class);

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

}
