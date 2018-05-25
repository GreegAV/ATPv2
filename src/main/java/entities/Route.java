package entities;

import dao.DAOBus;
import dao.DAODriver;

import java.util.Objects;

public class Route {
    private int routeID;
    private String routeName;
    private int busID;

    private int driverID;
    private String driverName;
    private String busName;

    public Route(int routeID, String routeName, int busID) {
        this.routeID = routeID;
        this.routeName = routeName;
        this.busID = busID;

        this.busName = setBusNameByID(busID);
        this.driverID = setDriverID(busID);
        this.driverName = setDriverNameByID(driverID);
    }

    private String setDriverNameByID(int driverID) {
        return (driverID == 0) ? "Admin" : DAODriver.getDriverNameByID(driverID);
    }

    private String setBusNameByID(int busID) {
        return (busID == 0) ? "Admin bus" : DAOBus.getBusNameByID(busID);
    }

    public int setDriverID(int busID) {
        return (busID == 0) ? 0 : DAODriver.getDriverIDByBusID(busID);
    }

    public int getDriverID() {
        return driverID;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getBusName() {
        return busName;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return routeID == route.routeID &&
                busID == route.busID &&
                driverID == route.driverID &&
                Objects.equals(routeName, route.routeName) &&
                Objects.equals(driverName, route.driverName) &&
                Objects.equals(busName, route.busName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(routeID, routeName, busID, driverID, driverName, busName);
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + routeID +
                ", routeName='" + routeName + '\'' +
                ", busID=" + busID +
                ", driverID=" + driverID +
                ", driverName='" + driverName + '\'' +
                ", busName='" + busName + '\'' +
                '}';
    }
}
