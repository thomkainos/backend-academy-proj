package org.example.daos;

import lombok.Getter;
import lombok.Setter;
import org.example.daos.interfaces.IJobRoleDao;
import org.example.exception.DatabaseConnectionException;
import org.example.exception.JobRoleDaoException;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailsResponse;
import org.example.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MySqIJobRoleDao implements IJobRoleDao {
    private DatabaseConnector databaseConnector;

    public MySqIJobRoleDao(final DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public List<JobRole> getJobRoles() throws JobRoleDaoException {
        List<JobRole> jobRoles = new ArrayList<>();

        // 1 = Open Status
        String getFromRolesQuery =
                "SELECT `role_id`, `role_name`, `location`,"
                        + "`capability`, `band`, `closing_date`, `role_status`"
                        + "FROM `roles` WHERE `role_status` = 1;";

        try (Connection connection = databaseConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getFromRolesQuery)) {

            while (resultSet.next()) {
                JobRole jobRole = new JobRole(
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name"),
                        resultSet.getString("location"),
                        resultSet.getString("capability"),
                        resultSet.getString("band"),
                        resultSet.getDate("closing_date"),
                        resultSet.getInt("role_status"));

                jobRoles.add(jobRole);
            }

        } catch (SQLException e) {
            throw new JobRoleDaoException("SQLException: " + e.getMessage(), e);

        } catch (DatabaseConnectionException e) {
            throw new JobRoleDaoException(
                    "DatabaseConnectionException: "
                            + "unable to connect to database", e);
        }
        return jobRoles;
    }

    @Override
    public JobRoleDetailsResponse getJobRoleById(final int id)
            throws JobRoleDaoException {

        try (Connection connection = databaseConnector.getConnection()) {
            String query = "Select `role_id`, role_name, location, capability,"
            + " band, closing_date, role_status, description,"
            + " responsibilities, job_link FROM roles WHERE role_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new JobRoleDetailsResponse(
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name"),
                        resultSet.getString("location"),
                        resultSet.getString("capability"),
                        resultSet.getString("band"),
                        resultSet.getDate("closing_date"),
                        resultSet.getInt("role_status"),
                        resultSet.getString("description"),
                        resultSet.getString("responsibilities"),
                        resultSet.getString("job_link")
                );

            }
            return new JobRoleDetailsResponse();

        } catch (SQLException e) {
            throw new JobRoleDaoException("SQLException: " + e.getMessage(), e);
        }
    }
}
