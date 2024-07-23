package org.example.daos;

import org.example.exception.DatabaseConnectionException;
import org.example.models.JobRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobRoleDao {
    public List<String> testConnection() throws SQLException,
            DatabaseConnectionException {
        List<String> databases = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SHOW DATABASES;");

            while (resultSet.next()) {
                databases.add(resultSet.getString("Database"));
            }
        }

        return databases;
    }

    public List<JobRole> getJobRoles() throws SQLException, DatabaseConnectionException {
        List<JobRole> jobRoles = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String getFromRolesQuery = "SELECT `role_id`, `role_name`, `location`, `capability`, `band`, " +
                    "`closing_date`, `role_status` FROM `roles` WHERE `role_status` = 1;";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(getFromRolesQuery);

            while (resultSet.next()) {
                JobRole jobRole = new JobRole (
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name"),
                        resultSet.getString("location"),
                        resultSet.getString("capability"),
                        resultSet.getString("band"),
                        resultSet.getDate("closing_date"),
                        resultSet.getInt("role_status"));

                jobRoles.add(jobRole);
            }
        }
        return jobRoles;
    }
}
