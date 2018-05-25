package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.ConnectionPool;
import entities.Driver;

import static service.ErrorLog.logError;

public class UserUtil {

    public Driver checkUserInDB(String loginName, String loginPassword) {

        try (Connection myConn = ConnectionPool.getInstance().getConnection();
             Statement myStmt = myConn.createStatement();
             ResultSet myRs = myStmt.executeQuery("select * from driver")) {
            while (myRs.next()) {
                String driverName = myRs.getString("driverName");
                if (driverName.equalsIgnoreCase(loginName)) {
                    String driverPass = myRs.getString("driverPassword");
                    if (checkUserPass(loginPassword, driverPass)) {
                        int userID = myRs.getInt("userID");
                        int busID = myRs.getInt("bus_busID");
                        int confirmed = myRs.getInt("confirmed");
                        return new Driver(userID, driverName, driverPass, busID, confirmed);
                    }
                }
            }
        } catch (SQLException e) {
            logError("Failed to get list of users", e);
        }
        return null;
    }

    public static String getDriverPassword(String driverName) {
        return new StringBuilder(driverName).reverse().toString();
    }

    private boolean checkUserPass(String loginPassword, String driverPass) {
        // страшная проверка валидности пароля.
        return loginPassword.equals(driverPass);
    }

    public String getUserPage(String loginName, String loginPassword) {
        Driver driver = checkUserInDB(loginName, loginPassword);
        if (driver != null) {
            if (isUserAdmin(driver)) {
                return "admin.jsp";
            }
            return "user.jsp";
        }
        return "userNotFound.jsp";
    }

    private boolean isUserAdmin(Driver driver) {
        return driver.getUserID() == 0;
    }
}
