package service;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserUtilTest {

    @Test
    public void getDriverPassword() {
        assertEquals("admin", UserUtil.getDriverPassword("admin"));
    }

    @Test
    public void checkUserInDB_Name() {
        assertEquals("Admin", UserUtil.checkUserInDB("admin", "admin").getDriverName());
    }

    @Test
    public void checkUserInDB_BusName() {
        assertEquals("-----", UserUtil.checkUserInDB("admin", "admin").getBusName());
    }

    @Test
    public void checkUserInDB_Password() {
        assertEquals("admin", UserUtil.checkUserInDB("admin", "admin").getDriverPassword());
    }

    @Test
    public void checkUserInDB_RouteName() {
        assertEquals("-----", UserUtil.checkUserInDB("admin", "admin").getRouteName());
    }

    @Test
    public void checkUserInDB_DriverID() {
        assertEquals(0, UserUtil.checkUserInDB("admin", "admin").getUserID());
    }

    @Test
    public void checkUserInDB_BusID() {
        assertEquals(0, UserUtil.checkUserInDB("admin", "admin").getBusID());
    }

    @Test
    public void checkUserInDB_RouteID() {
        assertEquals(0, UserUtil.checkUserInDB("admin", "admin").getRouteID());
    }



}