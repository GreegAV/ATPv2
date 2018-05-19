package dao;

import entities.Bus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOBus {
    private static Logger logger = Logger.getLogger(DAOBus.class);

    public static String getBusNameByID(int busID) {
        String busName;
        // getting busName by ID from DB

        busName="TODO busName from ID";

        return busName;
    }

    public List<Bus> getBuses() {

        List<Bus> buses = new ArrayList<>();

        // create sql statement
        String sql = "select * from bus";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logger.info("Received connection for getting list of buses.");

            // process result set
            while (myRs.next()) {
                // retrieve data from result set row
                int busID = myRs.getInt("busID");
                String busName = myRs.getString("busName");
                int driverID = myRs.getInt("driverID");
                int routeID = myRs.getInt("routeID");

                Bus tempBus = new Bus(busID, busName, driverID, routeID);
                buses.add(tempBus);
            }
        } catch (Exception e) {
            logger.error("Failed go get buses list. DAOBus.getbuses().");
            logger.error(e.getLocalizedMessage());
        }
        return buses;
    }
}
