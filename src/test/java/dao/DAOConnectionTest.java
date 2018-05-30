package dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static dao.ConfigManager.DBNAME;
import static org.junit.Assert.*;

public class DAOConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        assertNotNull(ConnectionPool.getInstance().getConnection());
    }

    @Test
    public void getStatement() throws SQLException {
        assertNotNull(DAOConnection.getStatement(ConnectionPool.getInstance().getConnection()));
    }

    @Test
    public void getConnection2() throws ClassNotFoundException, SQLException {
        ConfigManager configManager = ConfigManager.getInstance();

        String dbUser = configManager.getProperty(ConfigManager.USERNAME);
        String dbPassword = configManager.getProperty(ConfigManager.PASSWORD);
        String dbURL = configManager.getProperty(ConfigManager.URL);
        String dbName = configManager.getProperty(ConfigManager.DBNAME);
        String dbConnectionString = dbURL + dbName;
        //  Get a connection to database
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(dbConnectionString, dbUser, dbPassword);
        assertNotNull(connection);
    }

    @Test
    public void getResultSet() throws SQLException {
        assertNotNull(DAOConnection.getStatement(ConnectionPool.getInstance().getConnection()).executeQuery("SELECT * FROM driver"));
    }

    @Test
    public void getResultSet2() {
        assertEquals("Admin", DAODriver.getDriverNameByID(0));
    }
}