package dao;

import entities.Bus;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static service.ErrorLog.logError;
import static service.ErrorLog.logInfo;

public class DAOBusTest {

    @Test
    public void getBusNameByID() {
        assertEquals("-----", DAOBus.getBusNameByID(0));
    }

    @Test
    public void addBus() {
        String sqlInsert = "insert into bus "
                + "(busName) "
                + "values (?)";

        String testBus = "TestBus";
        int foundBusID = -1;
        String foundBusName = "";
        String findName4DeletedBus = "";
        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             PreparedStatement myStmt = myConn.prepareStatement(sqlInsert)) {
            logInfo("Received connection for adding new bus.");
            myConn.setAutoCommit(false);
            myConn.setSavepoint();
            myStmt.setString(1, testBus);
            myStmt.execute();
            myConn.commit();
            List<Bus> busList = DAOBus.getBuses();
            for (Bus tempBus : busList) {
                if (testBus.equals(tempBus.getBusName())) {
                    foundBusID = tempBus.getBusID();
                    break;
                }
            }
            foundBusName = DAOBus.getBusNameByID(foundBusID);
            DAOBus.deleteBus(foundBusID);
            myConn.rollback();

            findName4DeletedBus = DAOBus.getBusNameByID(foundBusID);

        } catch (SQLException e) {
            logError("Failed to add new bus.", e);
        }
        assertEquals(testBus, foundBusName);
        assertEquals("-1", findName4DeletedBus);
    }

    @Test
    public void deleteBus() {
    }
}