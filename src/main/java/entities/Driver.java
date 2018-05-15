package entities;

import java.util.Objects;

public class Driver {
    private int userID;
    private String driverName;
    private int routeID;
    private int busID;

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public Driver(int userID, String driverName, int routeID, int busID) {
        this.userID = userID;
        this.driverName = driverName;
        this.routeID = routeID;
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
