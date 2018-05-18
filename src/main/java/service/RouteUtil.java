package service;

import dao.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class RouteUtil {
    private static Logger logger = Logger.getLogger(RouteUtil.class);

    public String addRoute(String routeName) {
        try (Connection myConn = ConnectionPool.getInstance().getConnection()) {
            logger.info("Received connection for adding new route.");
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
            logger.error("Failed to add new route.");
            logger.error(e.getMessage());
            return "error.jsp";
        }
        return "admin.jsp";
    }

}
