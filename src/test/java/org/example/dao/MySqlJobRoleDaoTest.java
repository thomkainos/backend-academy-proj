package org.example.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.example.daos.MySqIJobRoleDao;
import org.example.daos.interfaces.IJobRoleDao;
import org.example.exception.JobRoleDaoException;
import org.example.models.JobRole;
import org.example.models.SingleJobRoleResponse;
import org.example.utils.DatabaseConnector;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MySqlJobRoleDaoTest {
    private Connection h2Connection;
    private DatabaseConnector mockDatabaseConnector;
    private IJobRoleDao IJobRoleDao;

    @BeforeEach
    public void setUp() throws Exception {
        h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        RunScript.execute(h2Connection, new FileReader("src/test/resources/schema.sql"));

        mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);
        when(mockDatabaseConnector.getConnection()).thenReturn(h2Connection);

        IJobRoleDao = new MySqIJobRoleDao(mockDatabaseConnector);
    }

    @AfterEach
    public void tearDown() throws Exception {
        h2Connection.close();
    }

    @Test
    public void getJobRoles_shouldReturnListOfJobRoles_whenDatabaseReturnsRowsOfJobRoles() throws JobRoleDaoException,
            Exception {
        RunScript.execute(h2Connection, new FileReader("src/test/resources/data.sql"));

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

    @Test
    public void getJobRoles_shouldReturnListOfEmptyList_whenDatabaseReturnsNoRows()
            throws Exception {
        List<JobRole> jobRoles = IJobRoleDao.getJobRoles();
        assertNotNull(jobRoles);
        assertEquals(0, jobRoles.size());
    }

    @Test
    public void getJobRoleById_shouldReturnJobRoleDetails_whenDatabaseReturnsJobRole()
            throws JobRoleDaoException, FileNotFoundException, SQLException,
            ParseException {

        RunScript.execute(h2Connection, new FileReader("src/test/resources/data.sql"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date closingDate = sdf.parse("2024-08-15");

        SingleJobRoleResponse jobRole = IJobRoleDao.getJobRoleById(1);
        assertEquals("Software Engineer", jobRole.getRoleName());
        assertEquals("London", jobRole.getLocation());
        assertEquals("Full Stack Development", jobRole.getCapability());
        assertEquals("Band 1", jobRole.getBand());
        assertEquals(closingDate, jobRole.getClosingDate());
        assertEquals(1, jobRole.getRoleStatus());
        assertEquals("A Software Engineer develops", jobRole.getDescription());
        assertEquals("Designing and developing software applications", jobRole.getResponsibilities());
        assertEquals("http://example.com/job-link/SoftwareEngineer", jobRole.getJob_link());
    }

    @Test
    public void getJobRoleById_shouldReturnNull_whenIdDoesNotExistInDatabase()
            throws JobRoleDaoException {
        SingleJobRoleResponse jobRole = IJobRoleDao.getJobRoleById(4);
        assertNull(jobRole);
    }
}
