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
            logError("Failed to get DriverID By BusID. DAODriver.getDriverIDByBusID().", e);
        }
        logInfo("Received driverID " + driverID + " by busID " + busID);
        return driverID;
    }

    public static String getDriverNameByID(int driverID) {
        // preparation
        String driverName = "-1";
        String sql = "select * from driver where userID=" + driverID;
        // execution
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

    public static String addDriver(String driverName, String driverPassword) {
        // setting SQL query for adding new driver into DB
        String sql = "insert into driver "
                + "(driverName, driverPassword, bus_busID, confirmed) "
                + "values (?,?,?,?)";
        // execution query
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for adding new driver.");

            myStmt.setString(1, driverName);
            myStmt.setString(2, UserUtil.getDriverPassword(driverPassword));
            myStmt.setInt(3, 0);
            myStmt.setInt(4, 1);  // new driver have nothing to confirm
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new driver.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public static void deleteDriver(int driverID) {
        Driver driver = DAODriver.getDriver(driverID);
        int routeID = driver.getRouteID();
        DAORoute.setRouteID(0, routeID);

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

    private static List<Driver> getDrivers() {
        //receiving full list of drivers
        List<Driver> drivers = new ArrayList<>();
        String sql = "select * from driver";
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of drivers.");

            // process result set
            while (myRs.next()) {
                int driverID = myRs.getInt("userID");
                String driverName = myRs.getString("driverName");
                String driverPassword = myRs.getString("driverPassword");
                int driverBusID = myRs.getInt("bus_busID");
                int driverConfirmed = myRs.getInt("confirmed");
                // Admin is not a driver :)
                if (driverID != 0)
                    drivers.add(new Driver(driverID, driverName, driverPassword, driverBusID, driverConfirmed));
            }
        } catch (Exception e) {
            logError("Failed to get drivers list. DAODriver.getDrivers().", e);
        }
        logInfo("Driverslist received. Total drivers: " + drivers.size());
        return drivers;
    }

    private static Driver getDriver(int driverID) {
        // preparing SQL query string
        String sql = "select * from driver where userID=" + driverID;
        // execute query
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting one driver by driverID.");
            //parsing results
            while (myRs.next()) {
                String driverName = myRs.getString("driverName");
                String driverPassword = myRs.getString("driverPassword");
                int driverBusID = myRs.getInt("bus_busID");
                int driverConfirmed = myRs.getInt("confirmed");
                return new Driver(driverID, driverName, driverPassword, driverBusID, driverConfirmed);
            }
        } catch (Exception e) {
            logError("Failed to get drivers list. DAODriver.getDrivers().", e);
        }
        return null;
    }

//    private static List<Driver> getFreeDrivers() {
//        List<Driver> drivers = DAODriver.getDrivers();
//        List<Driver> freeDrivers = new ArrayList<>();
//        for (Driver tempDriver : drivers) {
//            if (tempDriver.getBusID() == 0)
//                freeDrivers.add(tempDriver);
//        }
//        logInfo("Free driverslist received. Total free drivers: " + freeDrivers.size());
//        return freeDrivers;
//    }

    public static void prepareFreeListDrivers(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Driver> drivers = DAODriver.getFreeDrivers();
            request.getServletContext().setAttribute("FREEDRIVER_LIST", drivers);
        } catch (Exception e) {
            logError("Failed go get free drivers list. DAODriver.prepareFreeListDrivers", e);
        }
        logInfo("Drivers list updated.");
    }

    private static List<Driver> getFreeDrivers() {
        List<Driver> freeDrivers = new ArrayList<>();
        for (Driver tempDriver : DAODriver.getDrivers()) {
            if (tempDriver.getBusID()==0)
                freeDrivers.add(tempDriver);
        }
        return freeDrivers;
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

    public static void prepareFullListDrivers(HttpServletRequest request, HttpServletResponse response) {
        try {
            // getting  and setting initial settings
            int rowsPerPage = 7;
            String pageLine = "";
            List<Driver> drivers = new ArrayList<>();
            int currentPage = (int) request.getServletContext().getAttribute("currentPage");
            String locale = (String) request.getServletContext().getAttribute("theLocale");

            // preparing array of drivers
            List<Driver> tempDrivers = DAODriver.getDrivers();
//            tempDrivers.add(0, DAODriver.getDriver(0));

            // calculating pagination
            int startNumber = currentPage * rowsPerPage;
            int totalRows = tempDrivers.size();
            int endNumber = (totalRows < ((currentPage + 1) * rowsPerPage) ? totalRows : (currentPage + 1) * rowsPerPage);
            int numPages = (totalRows > rowsPerPage) ? (totalRows / rowsPerPage + 1) : 1;
            //getting one-page list of drivers
            for (int i = startNumber; i < endNumber; i++) {
                drivers.add(tempDrivers.get(i));
            }
            //formatting pagination line
            for (int i = 1; i < numPages; i++) {
                pageLine += "<a href=?currentPage=" + (i - 1) + "&theLocale=" + locale + "&command=CHANGEPAGE" + ">";
                pageLine += i;
                pageLine += "</a>&nbsp;&nbsp;|&nbsp;&nbsp;";
            }
            pageLine += "<a href=?currentPage=" + (numPages - 1) + "&theLocale=" + locale + "&command=CHANGEPAGE" + ">";
            pageLine += numPages;
            pageLine += "</a>";

            //sending results to servlet
            request.getServletContext().setAttribute("FULLDRIVER_LIST", drivers);
            request.getServletContext().setAttribute("paginator", pageLine);

        } catch (Exception e) {
            logError("Failed go get drivers list. DAODriver.prepareFullListDrivers", e);
        }
        logInfo("Drivers list updated.");
    }

    public static String setBusID(int busID, int driverID) {
        //formatting SQL query
        String sql = "update driver set bus_busID=?, confirmed=? where userID=?";

        // execute query
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for setting new bus(busID=" + busID + ") for the driver(driverID=" + driverID + ").");
            myStmt.setInt(1, busID);
            myStmt.setInt(2, 0);
            myStmt.setInt(3, driverID);
            myStmt.execute();
        } catch (SQLException e) {
            logError("Failed to set new route for the bus.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public static String setConfirmed(int driverID) {
        //formatting SQL query
        String sql = "update driver set confirmed=? where userID=?";
        // execute query
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for confirmation for the driver(driverID=" + driverID + ").");
            myStmt.setInt(1, 1);
            myStmt.setInt(2, driverID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to set confirmation.", e);
            return "error.jsp";
        }
        return "userConfirmed.jsp";
    }

    public static String freeDriver(int driverID) {
        String sql = "update driver set confirmed=1, bus_busID=0 where userID=?";
        // execute query
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for confirmation for the driver(driverID=" + driverID + ").");
            myStmt.setInt(1, driverID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to free driver (driverID=" + driverID + ").", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }
}
