package dao;

import entities.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAOBus {

    public static String getDriverNameByID(int assignedBus) {
        String driverName = "";
        String sql = "select * from bus where busid=" + assignedBus;
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting id of the driver by assignedBus.");
            while (myRs.next()) {
                driverName = DAODriver.getDriverNameByID(myRs.getInt("assignedDriver"));
            }
        } catch (Exception e) {
            logError("Failed go get name of the driver by id. DAODriver.getDriverNameByID().", e);
        }
        return driverName;
    }

    public List<Bus> getBuses() {

        List<Bus> buses = new ArrayList<>();

        String sql = "select * from bus";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting list of buses.");

            while (myRs.next()) {
                int busID = myRs.getInt("busID");
                String busName = myRs.getString("busName");
                int assignedDriver = myRs.getInt("assignedDriver");
                Bus tempBus = new Bus(busID, busName, assignedDriver);
                if (busID != 0)
                    buses.add(tempBus);
            }
        } catch (Exception e) {
            logError("Failed go get buses list. DAOBus.getbuses().", e);
        }
        return buses;
    }

    public static String getBusNameByID(int busID) {
        String busName = "";
        String sql = "select * from bus where busid=" + busID;

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery(sql)) {
            logInfo("Received connection for getting name of the bus by id.");
            while (myRs.next()) {
                busName = myRs.getString("busName");
            }
        } catch (Exception e) {
            logError("Failed go get name of the busby id. DAOBus.getBusNameByID().", e);
        }
        return busName;
    }

    public String addBus(String busModel) {
        String sql = "insert into bus "
                + "(busName, assignedDriver) "
                + "values (?,?)";

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sql)) {
            logInfo("Received connection for adding new bus.");

            // set the param values for the student
            myStmt.setString(1, busModel);
            myStmt.setInt(2, 0);
            myStmt.execute();

        } catch (SQLException e) {
            logError("Failed to add new bus.", e);
            return "error.jsp";
        }
        return "admin.jsp";
    }

    public void deleteBus(int busID) {
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

    public static void prepareListBuses(HttpServletRequest request, HttpServletResponse response) {
        try {
            DAOBus daoBus = new DAOBus();
            List<Bus> buses = daoBus.getBuses();

            // add buses to the request
            request.getServletContext().setAttribute("BUSES_LIST", buses);

        } catch (Exception e) {
            logError("Failed go get buses list.", e);
        }
        logInfo("Bus list updated.");
    }


}
