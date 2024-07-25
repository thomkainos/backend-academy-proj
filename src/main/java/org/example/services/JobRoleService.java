package org.example.services;

import org.example.exception.JobRoleDaoException;
import org.example.models.JobRoleResponse;
import org.example.daos.interfaces.JobRoleDao;
import org.example.mappers.JobRoleMapper;

import java.util.List;

public class JobRoleService {
    JobRoleDao jobRoleDao;

    public JobRoleService(final JobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRoleResponse> getAllOpenJobRoles() throws
            JobRoleDaoException {
        return JobRoleMapper.mapJobRoleListToJobRoleResponse(
                jobRoleDao.getJobRoles());
    }
}
