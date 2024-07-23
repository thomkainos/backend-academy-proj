package org.example.services;

import org.example.exception.DatabaseConnectionException;
import org.example.models.JobRoleResponse;
import org.example.daos.JobRoleDao;
import org.example.mappers.JobRoleMapper;

import java.sql.SQLException;
import java.util.List;



public class JobRoleService {
    JobRoleDao jobRoleDao;

    public JobRoleService(final JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRoleResponse> getAllOpenJobRoles() throws SQLException,
            DatabaseConnectionException {
        return JobRoleMapper.mapJobRoleListToJobRoleResponse(
                jobRoleDao.getJobRoles());
    }
}
