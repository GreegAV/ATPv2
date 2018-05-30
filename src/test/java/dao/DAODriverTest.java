package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class DAODriverTest {

    @Test
    public void getDriverIDByBusID() {
        assertNotNull(DAODriver.getDriverIDByBusID(0));
    }

    @Test
    public void getDriverNameByID() {
        assertEquals("Admin", DAODriver.getDriverNameByID(0));
    }
}