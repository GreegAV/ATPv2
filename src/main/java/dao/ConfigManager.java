package dao;

import java.util.ResourceBundle;

public class ConfigManager {

    static final String DRIVER = "DRIVER";
    static final String URL = "URL";
    static final String USERNAME = "USERNAME";
    static final String PASSWORD = "PASSWORD";
    static final String DBNAME = "DBNAME";
    static final String CONNECTIONPARAMS = "CONNECTIONPARAMS";

    private static final String BUNDLE_NAME = "config";
    private static ConfigManager instance;
    private ResourceBundle resource;

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
