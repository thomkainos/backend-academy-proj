package org.example.daos.interfaces;

import org.example.models.LoginRequest;
import org.example.models.User;

public interface IAuthDao {
    User getUser(LoginRequest loginRequest);
}
