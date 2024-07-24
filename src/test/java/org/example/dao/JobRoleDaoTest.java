package org.example.dao;

import org.example.daos.interfaces.JobRoleDao;
import org.example.exception.DatabaseConnectionException;
import org.example.models.JobRole;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


public class JobRoleDaoTest {
//    JobRoleDao jobRoleDao = new JobRoleDao();
//
//    @Test
//    void getJobRoles_shouldReturnListOfJobRoles_whenSqlStatementReturnsListOfJobRoles() {
//        String getFromRolesQuery = "SELECT `role_id`, `role_name`, `location`, `capability`, `band`, " +
//                "`closing_date`, `role_status` FROM `roles` WHERE `role_status` = 1;";
//
//        List<JobRole> jobRoles = new ArrayList<>();
//
//        try {
//
////            assertEquals(jobRoles, jobRoleDao.getJobRoles());
//
//        } catch (SQLException | DatabaseConnectionException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//    @Test
//    void getJobRoles_shouldThrowDatabaseConnectionException_whenDatabaseConnectorThrowsDatabaseConnectionException() {
//
//    }

}
