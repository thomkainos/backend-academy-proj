package org.example.daos.interfaces;

import org.example.exception.JobRoleDaoException;
import org.example.models.JobRole;
import org.example.models.SingleJobRoleResponse;

import java.util.List;

public interface IJobRoleDao {
    List<JobRole> getJobRoles() throws JobRoleDaoException;
    SingleJobRoleResponse getJobRoleById(int id) throws JobRoleDaoException;
}
