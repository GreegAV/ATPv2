package entities;

import dao.DAODriver;
import dao.DAORoute;

import java.util.Objects;

public class Bus {
    private int busID;
    private String busName;
    private int driverID;
    private int routeID;
    private String routeName;
    private String driverName;

    public Bus(int busID, String busName, int driverID, int routeID) {
        this.busID = busID;
        this.busName = busName;
        this.driverID = driverID;
        this.routeID = routeID;
        setDriverNameByID(driverID);
        setRouteNameByID(routeID);
    }

    public Bus(int busID, String busName, int driverID, int routeID, String routeName, String driverName) {
        this.busID = busID;
        this.busName = busName;
        this.driverID = driverID;
        this.routeID = routeID;
        this.routeName = routeName;
        this.driverName = driverName;
    }

    public void setRouteNameByID(int routeID) {
        this.routeName = DAORoute.getRouteNameByID(routeID);
    }

    public void setDriverNameByID(int driverID) {
        this.driverName = DAODriver.getDriverNameByID(driverID);
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
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

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return busID == bus.busID &&
                driverID == bus.driverID &&
                routeID == bus.routeID &&
                Objects.equals(busName, bus.busName) &&
                Objects.equals(routeName, bus.routeName) &&
                Objects.equals(driverName, bus.driverName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(busID, busName, driverID, routeID, routeName, driverName);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busID=" + busID +
                ", busName='" + busName + '\'' +
                ", routeName='" + routeName + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }

}
