package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigManagerTest {

    @Test
    public void getInstance() {
        assertNotNull(ConfigManager.getInstance());
    }

    @Test
    public void getProperty() {
        assertNotNull(ConfigManager.getInstance().getProperty(ConfigManager.DRIVER));
    }
}