package service;

import dao.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Driver;
import org.apache.log4j.Logger;


public class UserUtil {
    private static Logger logger = Logger.getLogger(UserUtil.class);

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
                        int routeID = myRs.getInt("routeID");
                        int busID = myRs.getInt("busID");
                        int confirmed = myRs.getInt("confirmed");
                        return new Driver(userID, driverName, driverPass, routeID, busID, confirmed);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private boolean checkUserPass(String loginPassword, String driverPass) {
        // страшная проверка валидности пароля.
        return loginPassword.equals(new StringBuilder(driverPass).reverse().toString());
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
