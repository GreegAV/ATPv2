package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class DAORouteTest {

    @Test
    public void getRouteIDByBusID() {
        assertNotNull(DAORoute.getRouteIDByBusID(0));
    }

    @Test
    public void getRouteNameByID() {
        assertEquals("-----", DAORoute.getRouteNameByID(0));
    }

}