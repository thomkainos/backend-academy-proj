package org.example.dao;

import org.example.daos.MySqIJobRoleDao;
import org.example.daos.MySqlAuthDao;
import org.example.daos.interfaces.IAuthDao;
import org.example.utils.DatabaseConnector;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

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
    public void getUser_
}
