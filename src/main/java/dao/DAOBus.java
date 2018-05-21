package dao;

import entities.Bus;
import entities.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAOBus {

    public static String getBusNameByID(int busID) {
        String busName;
        // getting busName by ID from DB

        busName = "TODO busName from ID";

        return busName;
    }

    public String addBus(String busModel, HttpServletRequest request, HttpServletResponse response) {
        try (Connection myConn = ConnectionPool.getInstance().getConnection()) {
            logInfo("Received connection for adding new bus.");
            // create sql for insert
            String sql = "insert into bus "
                    + "(busName, driverID, routeID) "
                    + "values (?, ?, ?)";

            PreparedStatement myStmt = myConn.prepareStatement(sql);
            // set the param values for the student
            myStmt.setString(1, busModel);
            myStmt.setInt(2, 0);
            myStmt.setInt(3, 0);
            // execute sql insert
            myStmt.execute();
            prepareListBuses(request, response);
        } catch (SQLException e) {
            logError("Failed to add new bus.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public List<Bus> getBuses() {

        List<Bus> buses = new ArrayList<>();

        // create sql statement
        String sql = "select * from bus";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of buses.");

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
            logError("Failed go get buses list. DAOBus.getbuses().", e);
        }
        return buses;
    }

    public static void prepareListBuses(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAOBus daoBus = new DAOBus();
            List<Bus> buses = daoBus.getBuses();

            // add buses to the request
            request.getServletContext().setAttribute("BUSES_LIST", buses);

        } catch (Exception e) {
            logError("Failed go get buses list.", e);
        }
    }

    public void deleteBus(int busID) {
        String sql = "delete from bus where busid=?";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql);) {
            logInfo("Received connection for driver deletion.");
            System.out.println("Received connection for driver deletion.");

            myStmt.setInt(1, busID);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to delete bus.", e);
        }
    }
}
