package org.example.services;

import org.example.daos.interfaces.IJobRoleDao;
import org.example.exception.JobRoleDaoException;
import org.example.mappers.JobRoleMapper;
import org.example.models.JobRoleDetailsResponse;
import org.example.models.JobRoleResponse;

import java.util.List;

public class JobRoleService {
    IJobRoleDao jobRoleDao;

    public JobRoleService(final IJobRoleDao jobRoleDao) {
        this.jobRoleDao = jobRoleDao;
    }

    public List<JobRoleResponse> getAllOpenJobRoles() throws
            JobRoleDaoException {
        return JobRoleMapper.mapJobRoleListToJobRoleResponse(
                jobRoleDao.getJobRoles());
    }

    public JobRoleDetailsResponse getJobRoleById(final int id) throws
            JobRoleDaoException {
        return jobRoleDao.getJobRoleById(id);
    }
}
