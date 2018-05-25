package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Driver;
import service.UserUtil;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAODriver {

    public static int getDriverIDByBusID(int busID) {
        int driverID = 0;
        String sql = "select * from driver where bus_busID=" + busID;

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("DAODriver.getDriverIDByBusID: Received connection for getting driverID by busID.");
            while (myRs.next()) {
                driverID = myRs.getInt("userID");
            }
        } catch (Exception e) {
            logError("Failed go get name of the driver by id. DAODriver.getRouteIDByDriverID().", e);
        }
        logInfo("Received driverID " + driverID + " by busID " + busID);
        return driverID;
    }

    public static String getDriverNameByID(int driverID) {
        String driverName = "-1";

        String sql = "select * from driver where userID=" + driverID;

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("DAODriver.getDriverNameByID: Received connection for getting driverName by driverID.");
            while (myRs.next()) {
                driverName = myRs.getString("driverName");
            }
        } catch (Exception e) {
            logError("Failed go get name of the driver by driverid. DAODriver.getRouteIDByDriverID().", e);
        }
        logInfo("Received driverName " + driverName + " by driverID " + driverID);
        return driverName;
    }

    public String addDriver(String driverName, String driverPassword) {
        String sql = "insert into driver "
                + "(driverName, driverPassword, bus_busID, confirmed) "
                + "values (?,?,?,?)";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for adding new driver.");

            myStmt.setString(1, driverName);
            myStmt.setString(2, UserUtil.getDriverPassword(driverPassword));
            myStmt.setInt(3, 0);
            myStmt.setInt(4, 0);
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

    public static List<Driver> getDrivers() {
        List<Driver> drivers = new ArrayList<>();
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
                int driverBusID = myRs.getInt("bus_busID");
                int driverConfirmed = myRs.getInt("confirmed");

                if (driverID != 0)
                    drivers.add(new Driver(driverID, driverName, driverPassword, driverBusID, driverConfirmed));
            }
        } catch (Exception e) {
            logError("Failed to get drivers list. DAODriver.getDrivers().", e);
        }
        logInfo("Driverslist received. Total drivers: " + drivers.size());
        return drivers;
    }

    public static void prepareListDrivers(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Driver> drivers = DAODriver.getDrivers();

            // add drivers to the request
            request.getServletContext().setAttribute("DRIVER_LIST", drivers);

        } catch (Exception e) {
            logError("Failed go get drivers list. DAODriver.prepareListDrivers", e);
        }
        logInfo("Drivers list updated.");
    }
}
