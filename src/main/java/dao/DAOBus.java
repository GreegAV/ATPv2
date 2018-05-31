package dao;

import entities.Bus;
import entities.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAOBus {

    public static List<Bus> getBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "select * from bus";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of buses.");

            while (myRs.next()) {
                int busID = myRs.getInt("busID");
                String busName = myRs.getString("busName");
                if (busID != 0)
                    buses.add(new Bus(busID, busName));
            }
        } catch (Exception e) {
            logError("Failed go get buses list. DAOBus.getbuses().", e);
        }
        logInfo("List of buses received. Total buses: " + buses.size());
        return buses;
    }

    public static String getBusNameByID(int busID) {
        String busName = "-1";
        String sql = "select * from bus where busid=" + busID;

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting name of the bus by id.");
            while (myRs.next()) {
                busName = myRs.getString("busName");
            }
        } catch (Exception e) {
            logError("Failed go get name of the bus by id. DAOBus.getBusNameByID().", e);
        }
        logInfo("Received busName " + busName + " by busID " + busID);
        return busName;
    }

    public static String addBus(String busModel) {

        String sql = "insert into bus "
                + "(busName) "
                + "values (?)";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for adding new bus.");

            myStmt.setString(1, busModel);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new bus.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public static void deleteBus(int busID) {
        DAODriver.setBusID(0, DAODriver.getDriverIDByBusID(busID));
        DAORoute.setRouteID(0, DAORoute.getRouteIDByBusID(busID));
        String sql = "delete from bus where busid=?";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for bus deletion.");

            myStmt.setInt(1, busID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to delete bus.", e);
        }
    }

    public static void prepareFreeListBuses(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Bus> freeBuses = DAOBus.getFreeBuses();
            request.getServletContext().setAttribute("FREEBUSES_LIST", freeBuses);
        } catch (Exception e) {
            logError("Failed go get full  buses list.", e);
        }
        logInfo("Full bus list updated.");
    }

    private static List<Bus> getFreeBuses() {
        List<Bus> freeBuses = new ArrayList<>();
        List<Route> routes = DAORoute.getRoutes();
        Bus tempBus;

        String sql = "select * from bus";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of buses.");

            while (myRs.next()) {
                int busID = myRs.getInt("busID");
                String busName = myRs.getString("busName");
                if ((busID != 0)&&(isFree(routes, busID))) {
                        freeBuses.add(new Bus(busID, busName));
                }
            }
        } catch (Exception e) {
            logError("Failed go get buses list. DAOBus.getbuses().", e);
        }
        logInfo("List of buses received. Total buses: " + freeBuses.size());
        return freeBuses;
    }

    private static boolean isFree(List<Route> routes, int busID) {
        for (Route tempRoute : routes) {
            if (tempRoute.getBusID() == busID) {
                return false;
            }
        }
        return true;
    }

    public static void prepareFullListBuses(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Bus> fullBuses = DAOBus.getBuses();
            fullBuses.add(0, new Bus(0, DAOBus.getBusNameByID(0)));
            request.getServletContext().setAttribute("FULLBUSES_LIST", fullBuses);
        } catch (Exception e) {
            logError("Failed go get full  buses list.", e);
        }
        logInfo("Full bus list updated.");
    }

    public static void prepareListBuses(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Bus> buses = DAOBus.getBuses();
            request.getServletContext().setAttribute("BUSES_LIST", buses);
        } catch (Exception e) {
            logError("Failed go get buses list.", e);
        }
        logInfo("Bus list updated.");
    }

}
