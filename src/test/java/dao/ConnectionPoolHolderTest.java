package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionPoolHolderTest {

    @Test
    public void getDataSource() {
        assertNotNull(ConnectionPoolHolder.getDataSource());
    }
}