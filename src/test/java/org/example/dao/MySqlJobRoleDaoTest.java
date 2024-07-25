package org.example.dao;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.daos.MySqlJobRoleDao;
import org.example.exception.JobRoleDaoException;
import org.example.exception.SqlException;
import org.example.models.JobRole;
import org.example.utils.DatabaseConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(DropwizardExtensionsSupport.class)
public class MySqlJobRoleDaoTest {






    @Test
    void getJobRoles_shouldReturnListOfJobRoles_whenSqlStatementReturnsListOfJobRoles() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        DatabaseConnector.setConn(conn); // pass in mock h2 db connection

        MySqlJobRoleDao mySqlJobRoleDao = new MySqlJobRoleDao(databaseConnector);

        List<JobRole> jobRoles = new ArrayList<>();

        assertEquals(7, jobRoles.size());
    }
}
