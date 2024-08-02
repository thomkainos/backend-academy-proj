package org.example.dao;

import org.example.daos.MySqlAuthDao;
import org.example.daos.interfaces.IAuthDao;
import org.example.exception.AuthDaoException;
import org.example.exception.DatabaseConnectionException;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.utils.DatabaseConnector;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MySqlAuthDaoTest {
    private Connection h2Connection;
    private DatabaseConnector mockDatabaseConnector;
    private IAuthDao IAuthDao;

    @BeforeEach
    public void setUp() throws Exception {
        h2Connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        RunScript.execute(h2Connection, new FileReader("src/test/resources/auth/sys_roles_schema.sql"));
        RunScript.execute(h2Connection, new FileReader("src/test/resources/auth/user_schema.sql"));

        mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);
        when(mockDatabaseConnector.getConnection()).thenReturn(h2Connection);

        IAuthDao = new MySqlAuthDao(mockDatabaseConnector);
    }

    @AfterEach
    public void tearDown() throws Exception {
        h2Connection.close();
    }

    @Test
    public void getUser_shouldReturnEmptyUserObject_whenUsernameDoesNotExist() throws Exception {
        RunScript.execute(h2Connection, new FileReader("src/test/resources/auth/whenUsernameDoesNotExist.sql"));

        LoginRequest loginRequest = new LoginRequest("user1", "user1");
        User user = IAuthDao.getUser(loginRequest);

        assertNotNull(user);
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    public void getUser_shouldReturnEmptyUserObject_whenPasswordIsInvalid() throws Exception {
        RunScript.execute(h2Connection, new FileReader("src/test/resources/auth/whenPasswordIsInvalid.sql"));

        LoginRequest loginRequest = new LoginRequest("user1", "");
        User user = IAuthDao.getUser(loginRequest);

        assertNotNull(user);
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    public void getUser_shouldReturnPopulatedUserObject_whenUserCredentialsAreValid() throws Exception {
        RunScript.execute(h2Connection, new FileReader("src/test/resources/auth/whenUserCredentialsAreValid.sql"));

        String testUsername = "user1";
        String testPassword = "user1";
        int testSysRoleId = 1;
        LoginRequest loginRequest = new LoginRequest(testUsername, testPassword);

        User user = IAuthDao.getUser(loginRequest);

        assertNotNull(user);
        assertEquals(testUsername, user.getUsername());
        assertEquals(testPassword, user.getPassword());
        assertEquals(testSysRoleId, user.getSysRoleId());
    }

    @Test
    public void getUser_shouldThrowAuthDaoException_whenDatabaseConnectorThrowsDatabaseConnectionException() throws Exception {
        mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);
        when(mockDatabaseConnector.getConnection()).thenThrow(
                DatabaseConnectionException.class);

        IAuthDao = new MySqlAuthDao(mockDatabaseConnector);
        assertThrows(AuthDaoException.class,
                () -> IAuthDao.getUser(new LoginRequest("user1", "user1")));
    }

    @Test
    public void getUser_shouldThrowAuthDaoException_whenDatabaseThrowsSqlException() throws Exception {
        mockDatabaseConnector = Mockito.mock(DatabaseConnector.class);
        when(mockDatabaseConnector.getConnection()).thenThrow(
                SQLException.class);

        IAuthDao = new MySqlAuthDao(mockDatabaseConnector);
        assertThrows(AuthDaoException.class,
                () -> IAuthDao.getUser(new LoginRequest("user1", "user1")));
    }
}
