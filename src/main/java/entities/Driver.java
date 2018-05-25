package entities;


import dao.DAOBus;
import dao.DAORoute;

import java.util.Objects;

public class Driver {
    private int userID;
    private String driverName;
    private String driverPassword;
    private int busID;
    private int confirmed;

    private int routeID;
    private String routeName;
    private String busName;

    public Driver(int userID, String driverName, String driverPassword, int busID, int confirmed) {
        this.userID = userID;
        this.driverName = driverName;
        this.driverPassword = driverPassword;
        this.busID = busID;
        this.confirmed = confirmed;
        this.routeID = setRouteID(busID);
        this.routeName = setRouteNameByID(routeID);
        this.busName = setBusNameByID(busID);
    }

    private String setBusNameByID(int busID) {
        return (busID == 0) ? "Admin bus" : DAOBus.getBusNameByID(busID);
    }

    private String setRouteNameByID(int routeID) {
        return (routeID == 0) ? "Admin route" : DAORoute.getRouteNameByID(routeID);
    }

    private int setRouteID(int busID) {
        return (busID == 0) ? 0 : DAORoute.getRouteIDByBusID(busID);
    }

    public int getRouteID() {
        return routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getBusName() {
        return busName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }
}
