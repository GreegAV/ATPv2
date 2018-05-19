package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Driver;

import org.apache.log4j.Logger;

public class DAODriver {
    private static Logger logger = Logger.getLogger(DAODriver.class);

    public List<Driver> getDrivers() {

        List<Driver> drivers = new ArrayList<>();

        // create sql statement
        String sql = "select * from driver";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logger.info("Received connection for getting list of drivers.");

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int driverID = myRs.getInt("userID");
                String driverName = myRs.getString("driverName");
                String driverPassword = myRs.getString("driverPassword");
                int driverRouteID = myRs.getInt("routeID");
                String driverRoute = DAODriver.getRouteNameByID(driverRouteID);
                int driverBusID = myRs.getInt("busID");
                String driverBus = DAOBus.getBusNameByID(driverBusID);
                int driverConfirmed = myRs.getInt("confirmed");

                // create new student object
                Driver tempDriver = new Driver(driverID, driverName, driverPassword, driverRouteID, driverBusID, driverConfirmed);
                // add it to the list of students
                drivers.add(tempDriver);
            }
        } catch (Exception e) {
            logger.error("Failed go get drivers list. DAODriver.getDrivers().");
            logger.error(e.getLocalizedMessage());
        }
        return drivers;
    }

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

    public static String getRouteNameByID(int driverRoute) {
        String routeName;
        // getting routename by ID from DB

        routeName = "TODO routeName";


        return routeName;
    }
}
