package dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class ConnectionPool {

    private static ConnectionPool instance = null;
    private DataSource pool;

    private ConnectionPool() {
        try {
            pool = ConnectionPoolHolder.getDataSource();
        } catch (Exception e) {
            logError("Unable to get datasource for connection pool.", e);
        }
    }

    public static ConnectionPool getInstance() {
            if (instance == null) {
                logInfo("ConnectioPool instance creation.");
                instance = new ConnectionPool();
            }
            return instance;
    }

    public Connection getConnection() throws SQLException {
        logInfo("Trying to get a connection from pool.");
        return pool.getConnection();
    }

    public static void closeConnection(Connection connection) throws SQLException {
        logInfo("Trying to close connection.");
        if (connection != null) {
            connection.close();
            logInfo("The closeConnection method finished successfully.");
        } else {
            logInfo("Connection is null!");
        }
    }
}
