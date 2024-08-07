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
import java.util.Optional;

public class MySqlAuthDao implements IAuthDao {
    private DatabaseConnector databaseConnector;

    public MySqlAuthDao(final DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Optional<User> getUser(final LoginRequest loginRequest) throws
            AuthDaoException {
        User user = new User();
        String getUserCredQuery =
                "SELECT `username`, `password`, `salt`,"
                        + " `sys_role_id` FROM `user`"
                        + " WHERE `username` = ?";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    getUserCredQuery);
            statement.setString(1, loginRequest.getUsername());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            String storedHash = resultSet.getString("password");
            if (!BCrypt.checkpw(loginRequest.getPassword(), storedHash)) {
                return Optional.empty();
            }

            user.setUsername(loginRequest.getUsername());
            user.setSysRoleId(resultSet.getInt("sys_role_id"));
        } catch (SQLException e) {
            throw new AuthDaoException(
                    "Unable to get information from database", e);
        } catch (DatabaseConnectionException e) {
            throw new AuthDaoException("Unable to connect to database", e);
        }
        return Optional.of(user);
    }
}
