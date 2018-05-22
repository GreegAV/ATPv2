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

    public List<Route> getRoutes() {

        List<Route> routes = new ArrayList<>();

        // create sql statement
        String sql = "select * from route";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of routes.");

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int routeID = myRs.getInt("routeID");
                String routeName = myRs.getString("routeName");
                int assigned2Driver = myRs.getInt("assigned2Driver");
                int assigned2Bus = myRs.getInt("assigned2Bus");

                Route tempRoute = new Route(routeID, routeName, assigned2Driver, assigned2Bus);
                routes.add(tempRoute);
            }
        } catch (Exception e) {
            logError("Failed go get routes list. DAORoute.getRoutes().", e);
        }
        return routes;
    }

    public static String getRouteNameByID(int routeID) {
        String routeName = "TODO routeName by ID "+routeID;
        String sql = "select * from route where routeid=" + routeID;

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting name of the bus by id.");
            while (myRs.next()) {
                routeName = myRs.getString("routeName");
            }
        } catch (Exception e) {
            logError("Failed go get name of the route by id. DAOBus.getRouteNameByID().", e);
        }
        return routeName;
    }

    public String addRoute(String routeName) {
        String sql = "insert into route "
                + "(routeName, assigned2driver, assigned2bus) "
                + "values (?, ?, ?)";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for adding new route.");

            myStmt.setString(1, routeName);
            myStmt.setInt(2, 0);
            myStmt.setInt(3, 0);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new route.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public void deleteRoute(int routeID) {
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
            DAORoute daoRoute = new DAORoute();
            List<Route> routes = daoRoute.getRoutes();

            // add buses to the request
            request.getServletContext().setAttribute("ROUTES_LIST", routes);

        } catch (Exception e) {
            logError("Failed go get routes list.", e);
        }
    }


}
