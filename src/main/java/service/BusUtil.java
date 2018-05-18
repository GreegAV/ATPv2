package service;

import dao.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class BusUtil {
    private static Logger logger = Logger.getLogger(BusUtil.class);

    public String addBus(String busModel) {
        try (Connection myConn = ConnectionPool.getInstance().getConnection()) {
            logger.error("Received connection for adding new bus.");
            // create sql for insert
            String sql = "insert into bus "
                    + "(busName, driverID, routeID) "
                    + "values (?, ?, ?)";

            PreparedStatement myStmt = myConn.prepareStatement(sql);
            // set the param values for the student
            myStmt.setString(1, busModel);
            myStmt.setInt(2, 0);
            myStmt.setInt(3, 0);
            System.out.println("before execution");
            System.out.println(myStmt);
            // execute sql insert
            System.out.println("!");
            System.out.println(myStmt.execute());
//            int result = myStmt.executeUpdate();
//            System.out.println(result);
            System.out.println("done");

        } catch (SQLException e) {
            logger.error("Failed to add new bus.");
            logger.error(e.getMessage());
            return "error.jsp";
        }
        return "admin.jsp";
    }

}
