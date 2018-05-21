package dao;

import java.sql.*;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAOConnection {

    public static ResultSet getResultSet(Statement statement, String sqlSelect) {
        try {
            if (statement != null) {
                return statement.executeQuery(sqlSelect);
            } else {
                logInfo("Null statement received!");
            }
        } catch (SQLException e) {
            logError("Failed to get resultset", e);
        }
        return null;
    }

    public static Statement getStatement(Connection connection) {
        try {
            if (connection != null) {
                return connection.createStatement();
            } else {
                logInfo("Null connection received!");
            }
        } catch (SQLException e) {
            logError("Failed to create statement", e);
        }
        return null;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logError("Failed to close resultset", e);
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logError("Failed to close statement", e);
            }
        }
    }
}
