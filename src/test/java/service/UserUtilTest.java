package service;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserUtilTest {

    @Test
    public void getDriverPassword() {
        assertEquals("admin", UserUtil.getDriverPassword("admin"));
    }
}