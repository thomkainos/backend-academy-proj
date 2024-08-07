package org.example.services;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.daos.interfaces.IAuthDao;
import org.example.exception.AuthDaoException;
import org.example.exception.InvalidCredentialsException;
import org.example.models.LoginRequest;
import org.example.models.User;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Getter @Setter @AllArgsConstructor
public class AuthService {
    private final IAuthDao authDao;
    private final Key key;

    public String login(final LoginRequest loginRequest) throws
            InvalidCredentialsException, AuthDaoException {
        Optional<User> user = authDao.getUser(loginRequest);

        if (user.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        return generateJwtToken(user.get());
    }

    private String generateJwtToken(final User user) {
        int tokenExpTimeInMillis = 18000000;

        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenExpTimeInMillis))
                .claim("Role", user.getSysRoleId())
                .subject(user.getUsername())
                .issuer("Kainos Job Role Manager")
                .signWith(key)
                .compact();
    }
}
