package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class DAOBusTest {

    @Test
    public void getBusNameByID() {
        assertEquals("-----",DAOBus.getBusNameByID(0));
    }
}