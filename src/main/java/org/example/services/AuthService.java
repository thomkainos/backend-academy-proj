package org.example.services;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.daos.interfaces.IAuthDao;
import org.example.exception.AuthDaoException;
import org.example.exception.InvalidCredentialsException;
import org.example.models.LoginRequest;
import org.example.models.LoginResponse;
import org.example.models.User;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Getter @Setter @AllArgsConstructor
public class AuthService {
    private final IAuthDao authDao;
    private final Key key;
    private static final long TOKEN_EXP_IN_MILLIS = 18000000;

    public LoginResponse login(final LoginRequest loginRequest) throws
            InvalidCredentialsException, AuthDaoException {
        Optional<User> user = authDao.getUser(loginRequest);

        if (user.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        String token = generateJwtToken(user.get());
        return new LoginResponse(token);
    }

    private String generateJwtToken(final User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()
                        + TOKEN_EXP_IN_MILLIS))
                .claim("Role", user.getSysRoleId())
                .subject(user.getUsername())
                .issuer("Kainos Job Role Manager")
                .signWith(key)
                .compact();
    }
}
