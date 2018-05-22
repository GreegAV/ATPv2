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
                String driverRoute = DAORoute.getRouteNameByID(driverRouteID);
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

    public static String getDriverNameByID(int driverID) {
        String driverName = "TODO getDriverNameByID";
        String sql = "select * from driver where userid=" + driverID;

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting name of the driver by id.");
            while (myRs.next()) {
                driverName = myRs.getString("driverName");
            }
        } catch (Exception e) {
            logError("Failed go get name of the driver by id. DAODriver.getDriverNameByID().", e);
        }
        return driverName;
    }

    public String addDriver(String driverName) {
        String sql = "insert into driver "
                + "(driverName, driverPassword, routeID, busID, confirmed) "
                + "values (?,?,?,?,?)";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for adding new driver.");

            myStmt.setString(1, driverName);
            myStmt.setString(2, new StringBuilder(driverName).reverse().toString());
            myStmt.setInt(3, 0);
            myStmt.setInt(4, 0);
            myStmt.setInt(5, 0);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new driver.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public void deleteDriver(int driverID) {
        String sql = "delete from driver where userid=?";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql);) {
            logInfo("Received connection for driver deletion.");

            myStmt.setInt(1, driverID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to delete driver.", e);
        }
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
}
