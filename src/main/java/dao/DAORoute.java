package dao;

import entities.Driver;
import entities.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DAORoute {
    private static Logger logger = Logger.getLogger(DAORoute.class);

    public static String getRouteNameByID(int routeID) {
        String routeName;
        // getting busName by ID from DB

        routeName="TODO routeName by ID";

        return routeName;
    }

    public List<Route> getRoutes() {

        List<Route> routes = new ArrayList<>();

        // create sql statement
        String sql = "select * from route";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logger.info("Received connection for getting list of routes.");

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int routeID = myRs.getInt("routeID");
                String routeName = myRs.getString("routeName");
                int assigned2Driver = myRs.getInt("assigned2Driver");
                int assigned2Bus = myRs.getInt("assigned2Bus");

                Route tempRoute = new Route(routeID, routeName, assigned2Driver,assigned2Bus);
                routes.add(tempRoute);
            }
        } catch (Exception e) {
            logger.error("Failed go get routes list. DAORoute.getRoutes().");
            logger.error(e.getLocalizedMessage());
        }
        return routes;
    }
}
