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
        List<JobRoleResponse> jobRoleResponses = new ArrayList<>();

        assertEquals(jobRoleResponses, jobRoleService.getAllOpenJobRoles());
    }

    @Test
    void getAllOpenJobRoles_shouldThrowJobRoleDaoException_whenDaoThrowsJobRoleDaoException ()
            throws JobRoleDaoException {
        Mockito.when(jobRoleDao.getJobRoles()).thenThrow(JobRoleDaoException.class);

        assertThrows(JobRoleDaoException.class,
                () -> jobRoleService.getAllOpenJobRoles());
    }
}
