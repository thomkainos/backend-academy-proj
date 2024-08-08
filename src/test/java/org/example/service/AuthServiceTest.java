package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.jsonwebtoken.Jwts;
import org.example.daos.interfaces.IAuthDao;
import org.example.exception.AuthDaoException;
import org.example.exception.InvalidCredentialsException;
import org.example.models.LoginRequest;
import org.example.models.LoginResponse;
import org.example.models.User;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.Optional;

import static org.example.utils.TestUtils.decodeBase64TokenSectionAndMapToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    IAuthDao IAuthDao = mock(IAuthDao.class);
    Key key = Jwts.SIG.HS256.key().build();
    AuthService authService = new AuthService(IAuthDao, key);

    @Test
    public void login_shouldReturnJwtTokenWithProperStructure_whenAuthDaoReturnsUserObject()
            throws AuthDaoException, InvalidCredentialsException,
            JsonProcessingException {
        LoginRequest loginRequest = new LoginRequest("user1", "user1");

        Optional<User> user = Optional.of(new User(
                "user1",
                1
        ));

        when(IAuthDao.getUser(loginRequest)).thenReturn(user);

        LoginResponse loginResponse = authService.login(loginRequest);
        String jwtTokenString = loginResponse.getToken();

        assertNotNull(jwtTokenString);

        String splitByPeriodRegex = "\\.";
        String[] tokenSections = jwtTokenString.split(splitByPeriodRegex);

        JsonNode headerNode = decodeBase64TokenSectionAndMapToJson(tokenSections[0]);
        JsonNode payloadNode = decodeBase64TokenSectionAndMapToJson(tokenSections[1]);

        assertEquals("HS256", headerNode.get("alg").asText());
        assertNotNull(payloadNode.get("iss").asText());
        assertNotNull(payloadNode.get("exp").asText());
        assertEquals(payloadNode.get("Role").asText(), "1");
        assertEquals(payloadNode.get("sub").asText(), "user1");
        assertEquals(payloadNode.get("iss").asText(), "Kainos Job Role Manager");
    }

    @Test
    public void login_shouldThrowInvalidCredentialsException_whenAuthDaoReturnsEmptyUserObject()
            throws AuthDaoException {
        LoginRequest loginRequest = new LoginRequest("user1", "user1");
        when(IAuthDao.getUser(loginRequest)).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> authService.login(loginRequest));
    }
}
