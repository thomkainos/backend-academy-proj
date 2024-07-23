package org.example.mappers;

import models.JobRole;
import models.JobRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public class JobRoleMapper {
    public static List<JobRoleResponse> mapJobRoleListToJobRoleResponse(
            final List<JobRole> jobRoles) {
        return jobRoles.stream()
                .map(jobRole -> new JobRoleResponse(jobRole.getRoleId(),
                        jobRole.getRoleName(), jobRole.getLocation(),
                        jobRole.getCapability(), jobRole.getBand(),
                        jobRole.getClosingDate(), jobRole.getRoleStatus()))
                .collect(Collectors.toList());
    }
}
