package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Driver;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAODriver {

    public List<Driver> getDrivers() {

        List<Driver> drivers = new ArrayList<>();

        // create sql statement
        String sql = "select * from driver";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of drivers.");

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
            logError("Failed go get drivers list. DAODriver.getDrivers().", e);
        }
        return drivers;
    }

    public static void prepareListDrivers(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAODriver daoDriver = new DAODriver();
            List<Driver> drivers = daoDriver.getDrivers();

            // add drivers to the request
            request.getServletContext().setAttribute("DRIVER_LIST", drivers);

        } catch (Exception e) {
            logError("Failed go get drivers list.", e);
        }
    }

    public String addDriver(String driverName, HttpServletRequest request, HttpServletResponse response) {
        try (Connection myConn = ConnectionPool.getInstance().getConnection()) {
            logInfo("Received connection for adding new driver.");
            // create sql for insert
            String sql = "insert into driver "
                    + "(driverName, driverPassword, routeID, busID, confirmed) "
                    + "values (?,?,?,?,?)";

            PreparedStatement myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, driverName);
            myStmt.setString(2, new StringBuilder(driverName).reverse().toString());
            myStmt.setInt(3, 0);
            myStmt.setInt(4, 0);
            myStmt.setInt(5, 0);
            myStmt.execute();
            prepareListDrivers(request, response);
        } catch (SQLException e) {
            logError("Failed to add new driver.", e);
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

    public void deleteDriver(int driverID) {
        String sql = "delete from driver where userid=?";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql);) {
            logInfo("Received connection for driver deletion.");
            System.out.println("Received connection for driver deletion.");

            myStmt.setInt(1, driverID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to delete driver.", e);
        }
    }
}
