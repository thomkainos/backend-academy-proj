package org.example.dao;

import org.example.daos.MySqIJobRoleDao;
import org.example.daos.interfaces.IJobRoleDao;
import org.example.exception.JobRoleDaoException;
import org.example.models.JobRole;
import org.example.models.JobRoleDetailsResponse;
import org.example.utils.DatabaseConnector;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class MySqlJobRoleDaoTest {
    private Connection h2Connection;
    private DatabaseConnector mockDatabaseConnector;
    private IJobRoleDao IJobRoleDao;

    @BeforeEach
    public void setUp() throws Exception {
        h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        h2Connection.createStatement().execute("DROP TABLE IF EXISTS roles");
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
            throws JobRoleDaoException, FileNotFoundException, SQLException {

        RunScript.execute(h2Connection, new FileReader("src/test/resources/data.sql"));

        LocalDate localDate = LocalDate.of(2024, 8, 15);
        Date closingDate = Date.valueOf(localDate);

        JobRoleDetailsResponse expectedResults = new JobRoleDetailsResponse(
                1,
                "Software Engineer",
                "London",
                "Full Stack Development",
                "Band 1",
                closingDate,
                1,
                "A Software Engineer develops",
                "Designing and developing software applications",
                "http://example.com/job-link/SoftwareEngineer"
        );

        JobRoleDetailsResponse jobRole = IJobRoleDao.getJobRoleById(1);
        assertThat(jobRole).isEqualToComparingFieldByField(expectedResults);
    }

    @Test
    public void getJobRoleById_shouldReturnEmptyJobRoleDetailsResponse_whenIdDoesNotExistInDatabase()
            throws JobRoleDaoException {
        JobRoleDetailsResponse emptyJobRoleDetailsObject = new JobRoleDetailsResponse();;
        JobRoleDetailsResponse jobRole = IJobRoleDao.getJobRoleById(4);

        assertThat(jobRole).isEqualToComparingFieldByField(emptyJobRoleDetailsObject);
    }
}
