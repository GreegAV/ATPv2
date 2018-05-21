package service;

import dao.ConnectionPool;

import java.sql.*;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class RouteUtil {

    public String addRoute(String routeName) {
        try (Connection myConn = ConnectionPool.getInstance().getConnection()) {
            logInfo("Received connection for adding new route.");
            // create sql for insert
            String sql = "insert into route "
                    + "(routeName) "
                    + "values (?)";

            PreparedStatement myStmt = myConn.prepareStatement(sql);
            // set the param values for the student
            myStmt.setString(1, routeName);
            // execute sql insert
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new route.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

}
