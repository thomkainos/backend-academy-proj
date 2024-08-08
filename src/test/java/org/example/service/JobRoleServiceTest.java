package org.example.service;

import org.example.daos.interfaces.IJobRoleDao;
import org.example.exception.JobRoleDaoException;
import org.example.models.JobRoleDetailsResponse;
import org.example.models.JobRoleResponse;
import org.example.services.JobRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class JobRoleServiceTest {
    IJobRoleDao IJobRoleDao = mock(IJobRoleDao.class);
    JobRoleService jobRoleService = new JobRoleService(IJobRoleDao);

    @Test
    void getAllOpenJobRoles_shouldReturnListOfJobRoleResponses_whenDaoReturnsListOfJobRoles () throws
            JobRoleDaoException {
        List<JobRoleResponse> jobRoleResponses = new ArrayList<>();

        assertEquals(jobRoleResponses, jobRoleService.getAllOpenJobRoles());
    }

    @Test
    void getAllOpenJobRoles_shouldThrowJobRoleDaoException_whenDaoThrowsJobRoleDaoException ()
            throws JobRoleDaoException {
        Mockito.when(IJobRoleDao.getJobRoles()).thenThrow(JobRoleDaoException.class);

        assertThrows(JobRoleDaoException.class,
                () -> jobRoleService.getAllOpenJobRoles());
    }

    @Test
    void getJobRoleById_shouldReturnSingleJobRoleResponse_whenDaoReturnsJobRole()
            throws JobRoleDaoException {

        LocalDate localDate = LocalDate.of(2024, 8, 15);
        Date closingDate = Date.valueOf(localDate);

        JobRoleDetailsResponse expectedResult = new JobRoleDetailsResponse(
                1,
                "Software Engineer",
                "Toronto",
                "very capable",
                "band 1",
                closingDate,
                1,
                "coding a lot",
                "very responsible",
                "http://example.com/job-link/SoftwareEngineer"

        );

        Mockito.when(IJobRoleDao.getJobRoleById(1)).thenReturn(expectedResult);
        JobRoleDetailsResponse result = jobRoleService.getJobRoleById(1);

        assertEquals(expectedResult, result);
    }

    @Test
    void getJobRoleById_shouldThrowJobRoleDaoException_whenDaoThrowsJobRoleDaoException()
            throws JobRoleDaoException {
        Mockito.when(IJobRoleDao.getJobRoleById(10)).thenThrow(JobRoleDaoException.class);

        assertThrows(JobRoleDaoException.class,
                () -> jobRoleService.getJobRoleById(10));
    }
}
