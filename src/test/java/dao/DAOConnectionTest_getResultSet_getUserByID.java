package dao;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DAOConnectionTest_getResultSet_getUserByID {

    @Test
    public void getResultSet() {
        assertEquals("Admin",DAODriver.getDriverNameByID(0));
    }
}