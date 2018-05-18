package service;

import dao.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class DriverUtil {
    private static Logger logger = Logger.getLogger(DriverUtil.class);

    public String addDriver(String driverName) {
        try (Connection myConn = ConnectionPool.getInstance().getConnection()) {
            logger.info("Received connection for adding new driver.");
            // create sql for insert
            String sql = "insert into driver "
                    + "(driverName, driverPassword, routeID, busID, confirmed) "
                    + "values (?,?,?,?,?)";

            PreparedStatement myStmt = myConn.prepareStatement(sql);
            // set the param values for the student
            myStmt.setString(1, driverName);
            myStmt.setString(2, new StringBuilder(driverName).reverse().toString());
            myStmt.setInt(3, 0);
            myStmt.setInt(4, 0);
            myStmt.setInt(5, 0);
            // execute sql insert
            myStmt.execute();

        } catch (SQLException e) {
            logger.error("Failed to add new driver.");
            logger.error(e.getMessage());
            return "error.jsp";
        }
        return "admin.jsp";
    }

}
