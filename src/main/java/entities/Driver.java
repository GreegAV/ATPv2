package entities;

import dao.DAOBus;
import dao.DAORoute;

import java.util.Objects;

public class Driver {
    private int userID;
    private String driverName;
    private String driverPassword;
    private int routeID;
    private int busID;
    private int confirmed;
    private String routeName;
    private String busName;

    public Driver(int userID, String driverName, String driverPassword, int routeID, int busID, int confirmed) {
        this.userID = userID;
        this.driverName = driverName;
        this.driverPassword = driverPassword;
        this.routeID = routeID;
        this.busID = busID;
        this.confirmed = confirmed;
        setRouteName(routeID);
        setBusName(busID);
    }

    public void setBusName(int busID) {
        this.busName = DAOBus.getBusNameByID(busID);
    }

    public void setRouteName(int routeID) {
        this.routeName = DAORoute.getRouteNameByID(routeID);
    }

    public String getRouteName() {
        return routeName;
    }

    public String getBusName() {
        return busName;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public boolean isConfirmed() {
        return confirmed>0;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
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

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return userID == driver.userID &&
                routeID == driver.routeID &&
                busID == driver.busID &&
                Objects.equals(driverName, driver.driverName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userID, driverName, routeID, busID);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "userID=" + userID +
                ", driverName='" + driverName + '\'' +
                ", routeID=" + routeID +
                ", busID=" + busID +
                '}';
    }
}
