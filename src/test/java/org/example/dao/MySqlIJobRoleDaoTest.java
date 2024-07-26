package org.example.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.example.daos.MySqlIJobRoleDao;
import org.example.daos.interfaces.IJobRoleDao;
import org.example.exception.JobRoleDaoException;
import org.example.models.JobRole;
import org.example.utils.DatabaseConnector;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MySqlIJobRoleDaoTest {
    private Connection h2Connection;
    private DatabaseConnector mockDatabaseConnector;
    private IJobRoleDao IJobRoleDao;

    @BeforeEach
    public void setUp() throws Exception {
        h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        RunScript.execute(h2Connection, new FileReader("src/test/resources/schema.sql"));
        RunScript.execute(h2Connection, new FileReader("src/test/resources/data.sql"));

        mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);
        when(mockDatabaseConnector.getConnection()).thenReturn(h2Connection);

        IJobRoleDao = new MySqlIJobRoleDao(mockDatabaseConnector);
    }

    @AfterEach
    public void tearDown() throws Exception {
        h2Connection.close();
    }

    @Test
    public void getJobRoles_shouldReturnListOfJobRoles_whenDatabaseReturnsRowsOfJobRoles() throws JobRoleDaoException {
        List<JobRole> jobRoles = IJobRoleDao.getJobRoles();
        assertNotNull(jobRoles);
        assertEquals(2, jobRoles.size());

        JobRole firstRole = jobRoles.get(0);
        assertEquals(1, firstRole.getRoleId());
        assertEquals("Software Engineer", firstRole.getRoleName());

        JobRole secondRole = jobRoles.get(1);
        assertEquals(2, secondRole.getRoleId());
        assertEquals("Product Manager", secondRole.getRoleName());
    }
}
