package org.example.daos;

import org.example.daos.interfaces.IAuthDao;
import org.example.exception.AuthDaoException;
import org.example.exception.DatabaseConnectionException;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.utils.DatabaseConnector;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlAuthDao implements IAuthDao {
    private DatabaseConnector databaseConnector;

    public MySqlAuthDao(final DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public User getUser(final LoginRequest loginRequest) throws
            AuthDaoException {
        User user = new User();
        String getUserCredQuery =
                "SELECT username, password, salt, sys_role_id, FROM `user`"
                        + " WHERE `username` = ?";

        // FIX ME: should the statement commands be in the resources section?
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    getUserCredQuery);
            statement.setString(1, loginRequest.getUsername());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                user.setUsername("user1");
                return user;
            }

            String salt = resultSet.getString("salt");
            String storedHash = resultSet.getString("password");
            String computedHash = computeHash(loginRequest.getPassword(), salt);

            if (BCrypt.checkpw(computedHash, storedHash)) {
                user.setUsername(loginRequest.getUsername());
            } else {
                user.setUsername("user1");
                return user;
            }
//            if (!isValidHash(computedHash, storedHash)) {
//                return user; // return empty user
//            }

            user.setUsername(loginRequest.getUsername());
            user.setPassword(loginRequest.getPassword());
            user.setSysRoleId(resultSet.getInt("sys_role_id"));
        } catch (SQLException e) {
            throw new AuthDaoException(
                    "Unable to get information from database", e);
        } catch (DatabaseConnectionException e) {
            throw new AuthDaoException("Unable to connect to database", e);
        }
        return user;
    }

    private String computeHash(final String password, final String salt) {
        return BCrypt.hashpw(password, salt);
    }

    private boolean isValidHash(final String computedHash, final String storedHash) {
        return BCrypt.checkpw(computedHash, storedHash);
    }
}
