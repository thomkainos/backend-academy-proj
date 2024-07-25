package org.example.service;

import org.example.daos.interfaces.JobRoleDao;
import org.example.exception.DatabaseConnectionException;
import org.example.exception.JobRoleDaoException;
import org.example.exception.SqlException;
import org.example.models.JobRole;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    JobRoleDao jobRoleDao = mock(JobRoleDao.class);

    JobRoleService jobRoleService = new JobRoleService(jobRoleDao);

    @Test
    void getAllOpenJobRoles_shouldReturnListOfJobRoleResponses_whenDaoReturnsListOfJobRoles () throws
            JobRoleDaoException {
        List<JobRole> jobRoles = new ArrayList();
        List<JobRoleResponse> jobRoleResponses = new ArrayList();

        assertEquals(jobRoleResponses, jobRoleService.getAllOpenJobRoles());
    }

    @Test
    void getAllOpenJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException ()
            throws JobRoleDaoException {
        Mockito.when(jobRoleDao.getJobRoles()).thenThrow(SqlException.class);

        assertThrows(SqlException.class,
                () -> jobRoleService.getAllOpenJobRoles());
    }

    @Test
    void getAllOpenJobRoles_shouldThrowDatabaseConnectionException_whenDaoThrowsDatabaseConnectionException ()
            throws JobRoleDaoException {
        Mockito.when(jobRoleDao.getJobRoles()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobRoleService.getAllOpenJobRoles());
    }
}
