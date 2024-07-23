package org.example.mappers;

import org.example.models.JobRole;
import org.example.models.JobRoleResponse;

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
