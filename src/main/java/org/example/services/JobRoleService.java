package org.example.services;

import org.example.exception.JobRoleDaoException;
import org.example.models.JobRoleResponse;
import org.example.daos.interfaces.IJobRoleDao;
import org.example.mappers.JobRoleMapper;

import java.util.List;

public class JobRoleService {
    IJobRoleDao IJobRoleDao;

    public JobRoleService(final IJobRoleDao IJobRoleDao) {
        this.IJobRoleDao = IJobRoleDao;
    }

    public List<JobRoleResponse> getAllOpenJobRoles() throws
            JobRoleDaoException {
        return JobRoleMapper.mapJobRoleListToJobRoleResponse(
                IJobRoleDao.getJobRoles());
    }
}
