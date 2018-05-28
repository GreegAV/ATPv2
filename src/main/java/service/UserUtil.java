package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.ConnectionPool;
import entities.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static service.ErrorLog.logError;

public class UserUtil {

    private static Driver checkUserInDB(String loginName, String loginPassword) {

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

    public static String getDriverPassword(String driverPassword) {
        // Страшная модификация полученного из базы значения и получение из него пароля пользователя.
        return driverPassword;
    }

    private static boolean checkUserPass(String loginPassword, String driverPass) {
        // страшная проверка валидности пароля.
        return loginPassword.equals(driverPass);
    }

    public static String getUserPage(HttpServletRequest request, HttpServletResponse response) {
        String loginName = request.getParameter("nameInput");
        String loginPassword = request.getParameter("passInput");
        Driver driver = checkUserInDB(loginName, loginPassword);
        if (driver != null) {
            request.getServletContext().setAttribute("LOGGED_USER", driver);
//            if (isUserAdmin(driver)) {
                return (isUserAdmin(driver))?"admin.jsp":"user.jsp";
//            }
//            return "user.jsp";
        }
        return "userNotFound.jsp";
    }

    private static boolean isUserAdmin(Driver driver) {
        return driver.getUserID() == 0;
    }
}
