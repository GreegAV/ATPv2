package entities;

import dao.DAODriver;
import dao.DAORoute;

import java.util.Objects;

public class Bus {
    private int busID;
    private String busName;
    private int assignedDriver;

    private int routeID;
    private String routeName;
    private String driverName;

    public Bus(int busID, String busName, int assignedDriver) {
        this.busID = busID;
        this.busName = busName;
        this.assignedDriver = assignedDriver;
        setRouteID(assignedDriver);
        setRouteNameByID(routeID);
        setDriverNameByID(assignedDriver);
    }

    public int getBusID() {
        return busID;
    }

    public String getBusName() {
        return busName;
    }

    public int getAssignedDriver() {
        return assignedDriver;
    }

    public int getRouteID() {
        return routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public void setAssignedDriver(int assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    public void setRouteID(int assignedDriver) {
        this.routeID = (assignedDriver == 0) ? 0 : DAODriver.getRouteIDByDriverID(assignedDriver);
    }

    private void setRouteNameByID(int routeID) {
        this.routeName = (routeID == 0) ? "Admin route" : DAORoute.getRouteNameByID(routeID);
    }

    private void setDriverNameByID(int assignedDriver) {
        this.driverName = (assignedDriver == 0) ? "Admin" : DAODriver.getDriverNameByID(assignedDriver);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return busID == bus.busID &&
                assignedDriver == bus.assignedDriver &&
                routeID == bus.routeID &&
                Objects.equals(busName, bus.busName) &&
                Objects.equals(routeName, bus.routeName) &&
                Objects.equals(driverName, bus.driverName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(busID, busName, assignedDriver, routeID, routeName, driverName);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busID=" + busID +
                ", busName='" + busName + '\'' +
                ", assignedDriver=" + assignedDriver +
                ", routeID=" + routeID +
                ", routeName='" + routeName + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
