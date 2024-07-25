package org.example.dao;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import io.dropwizard.testing.junit.DAOTestRule;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.daos.MySqlJobRoleDao;
import org.example.daos.interfaces.JobRoleDao;
import org.example.exception.JobRoleDaoException;
import org.example.exception.SqlException;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.example.utils.DatabaseConnector;
//import org.junit.Before;
//import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(DropwizardExtensionsSupport.class)
public class MySqlJobRoleDaoTest {

//    public DAOTestExtension database = DAOTestExtension
//            .newBuilder()
//            .addEntityClass(JobRole.class)
//            .build();
//
//    private org.hibernate.SessionFactory sessionFactory;
//
//    @BeforeEach
//    public void setUp() {
//        sessionFactory = database.getSessionFactory();
//    }

    @Test
    public void getJobRoles_shouldReturnListOfJobRoles_whenDatabaseReturnsRowsOfJobRoles()
            throws SQLException, JobRoleDaoException {
        // Add test data to db
//        List<JobRole> jobRoleExpected = List.of(
//            new JobRole(
//                    1,
//                    "Software Developer",
//                    "Toronto",
//                    "Digital Services",
//                    "Trainee",
//                    Date.valueOf("2024-09-12 23:59:59"),
//                    1
//            )
//        );

        // Mock out connection
        DatabaseConnector databaseConnector = mock(DatabaseConnector.class);
        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("role_id")).thenReturn(1);
        when(resultSet.getString("role_name")).thenReturn("Software Developer");
        when(resultSet.getString("location")).thenReturn("Toronto");
        when(resultSet.getString("capability")).thenReturn("Digital Services");
        when(resultSet.getString("band")).thenReturn("Trainee");
        when(resultSet.getDate("closing_date")).thenReturn(new Date(1999, 11, 27));
        when(resultSet.getInt("band")).thenReturn(1);

        // Call dao method
        MySqlJobRoleDao mySqlJobRoleDao = new MySqlJobRoleDao(databaseConnector);
        List<JobRole> jobRoles = mySqlJobRoleDao.getJobRoles();

        // Assert
        assertEquals(jobRoles.get(0).getRoleId(), 1);
    }

}
