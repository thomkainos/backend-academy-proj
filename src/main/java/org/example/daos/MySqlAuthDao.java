package org.example.daos;

import org.example.daos.interfaces.IAuthDao;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.utils.DatabaseConnector;

public class MySqlAuthDao implements IAuthDao {
    private DatabaseConnector databaseConnector;

    public MySqlAuthDao(final DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public User getUser(final LoginRequest loginRequest) {
        // Connect to db

        // Get user creds if they exist


        // Return user object

        return new User();
    }
}
