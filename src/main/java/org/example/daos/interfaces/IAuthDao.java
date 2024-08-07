package org.example.daos.interfaces;

import org.example.exception.AuthDaoException;
import org.example.models.LoginRequest;
import org.example.models.User;

import java.util.Optional;

public interface IAuthDao {
    Optional<User> getUser(LoginRequest loginRequest) throws AuthDaoException;
}
