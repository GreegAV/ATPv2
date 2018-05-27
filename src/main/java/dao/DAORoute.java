package dao;

import entities.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAORoute {

    public static int getRouteIDByBusID(int busID) {
        int routeID = 0;
        String sql = "select * from route where bus_busID=" + busID;
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("DAORoute.getRouteIDByBusID.\n\r Received connection for getting routeID by busID.");
            while (myRs.next()) {
                routeID = myRs.getInt("routeID");
            }
        } catch (Exception e) {
            logError("Failed go getroute id by bus id. DAORoute.getRouteIDByBusID().", e);
        }
        logInfo("Received routeID " + routeID + " by busID " + busID);
        return routeID;
    }

    public static String getRouteNameByID(int routeID) {
        String routeName = "-1";
        String sql = "select * from route where routeID=" + routeID;

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("DAORoute.getRouteNameByID: Received connection for getting routeName by routeID.");
            while (myRs.next()) {
                routeName = myRs.getString("routeName");
            }
        } catch (Exception e) {
            logError("Failed go get name of the route by routeID. DAORoute.getRouteNameByID().\n\r", e);
        }
        logInfo("Received routeName " + routeName + " by routeID " + routeID);
        return routeName;
    }

    private static List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();

        String sql = "select * from route";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of routes.");

            while (myRs.next()) {
                int routeID = myRs.getInt("routeID");
                String routeName = myRs.getString("routeName");
                int busID = myRs.getInt("bus_busid");

//                if (routeID != 0)
                    routes.add(new Route(routeID, routeName, busID));
            }
        } catch (Exception e) {
            logError("Failed go get routes list. DAORoute.getRoutes().", e);
        }
        logInfo("Routeslist received. Total routes: " + routes.size());
        return routes;
    }

    public static String addRoute(String routeName) {
        String sql = "insert into route "
                + "(routeName, bus_busID) "
                + "values (?, ?)";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for adding new route.");

            myStmt.setString(1, routeName);
            myStmt.setInt(2, 0);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new route.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public static void deleteRoute(int routeID) {
        String sql = "delete from route where routeid=?";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql);) {
            logInfo("Received connection for route deletion.");

            myStmt.setInt(1, routeID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to delete route.", e);
        }
    }

    public static void prepareListRoutes(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Route> routes = DAORoute.getRoutes();
            request.getServletContext().setAttribute("ROUTES_LIST", routes);
        } catch (Exception e) {
            logError("Failed go get routes list.", e);
        }
        logInfo("Route list updated.");
    }

    public static String setRouteID(int busID, int routeID) {
        String sql = "update route set bus_busID=? where routeID=?";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for setting new route for the bus.");

            myStmt.setInt(1, busID);
            myStmt.setInt(2, routeID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to set new route for the bus.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }
}
