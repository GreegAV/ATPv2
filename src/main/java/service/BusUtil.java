package service;

import dao.ConnectionPool;

import java.sql.*;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class BusUtil {

    public String addBus(String busModel) {
        try (Connection myConn = ConnectionPool.getInstance().getConnection()) {
            logInfo("Received connection for adding new bus.");
            // create sql for insert
            String sql = "insert into bus "
                    + "(busName, driverID, routeID) "
                    + "values (?, ?, ?)";

            PreparedStatement myStmt = myConn.prepareStatement(sql);
            // set the param values for the student
            myStmt.setString(1, busModel);
            myStmt.setInt(2, 0);
            myStmt.setInt(3, 0);
            // execute sql insert
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new bus.",e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

}
