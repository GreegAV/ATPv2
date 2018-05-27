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

    public Bus(int busID, String busName) {
        this.busID = busID;
        this.busName = busName;
        this.driverID = setDriverID(busID);
        this.routeID = setRouteID(busID);
        this.driverName = setDriverNameByID(this.driverID);
        this.routeName = setRouteNameByID(this.routeID);
    }

    public String getRouteName() {
        return routeName;
    }

    private String setRouteNameByID(int routeID) {
        return DAORoute.getRouteNameByID(routeID);
    }

    public String getDriverName() {
        return driverName;
    }

    private String setDriverNameByID(int driverID) {
        return DAODriver.getDriverNameByID(driverID);
    }

    public int getRouteID() {
        return routeID;
    }

    private int setRouteID(int busID) {
        return (busID == 0) ? 0 : DAORoute.getRouteIDByBusID(busID);
    }

    public int getDriverID() {
        return driverID;
    }

    private int setDriverID(int busID) {
        return (busID == 0) ? 0 : DAODriver.getDriverIDByBusID(busID);
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
                ", driverID=" + driverID +
                ", routeID=" + routeID +
                ", routeName='" + routeName + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
