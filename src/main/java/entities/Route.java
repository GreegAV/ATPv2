package entities;

import dao.DAOBus;
import dao.DAODriver;

import java.util.Objects;

public class Route {
    private int routeID;
    private String routeName;
    private int assignedBus;

    private int driverID;
    private String driverName;
    private String busName;

    public Route(int routeID, String routeName, int assignedBus) {
        this.routeID = routeID;
        this.routeName = routeName;
        this.assignedBus = assignedBus;
        setDriverID(routeID);
        setDriverNameByID(this.driverID);
        setBusNameByID(this.assignedBus);
    }

    public int getRouteID() {
        return routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getAssignedBus() {
        return assignedBus;
    }

    public int getDriverID() {
        return driverID;
    }

    public String getBusName() {
        return busName;
    }

    public String getDriverName() {
        return driverName;
    }

    ///////////
    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setAssignedBus(int assignedBus) {
        this.assignedBus = assignedBus;
    }
    ///////////

    /////////////////////////////////
    private void setDriverNameByID(int driverID) {
        this.driverName = DAODriver.getDriverNameByID(driverID);
    }

    private void setDriverID(int routeID) {
        this.driverID = DAODriver.getDriverIDByRouteID(routeID);
    }

    private void setBusNameByID(int busID) {
        this.busName = DAOBus.getBusNameByID(busID);
    }
    ///////////////////////////////


    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + routeID +
                ", routeName='" + routeName + '\'' +
                ", assignedBus=" + assignedBus +
                ", driverID=" + driverID +
                ", driverName='" + driverName + '\'' +
                ", busName='" + busName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return routeID == route.routeID &&
                assignedBus == route.assignedBus &&
                driverID == route.driverID &&
                Objects.equals(routeName, route.routeName) &&
                Objects.equals(driverName, route.driverName) &&
                Objects.equals(busName, route.busName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(routeID, routeName, assignedBus, driverID, driverName, busName);
    }
}
