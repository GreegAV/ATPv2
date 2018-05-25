package entities;

import dao.DAOBus;
import dao.DAODriver;

import java.util.Objects;

public class Route {
    private int routeID;
    private String routeName;
    private int assignedBus;

    //    private int driverID;
    private String driverName;
    private String busName;

    public Route(int routeID, String routeName, int assignedBus) {
        this.routeID = routeID;
        this.routeName = routeName;
        this.assignedBus = assignedBus;
//        setDriverID(routeID);
        setDriverNameByID(assignedBus);
        setBusNameByID(assignedBus);
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

//    public int getDriverID() {
//        return driverID;
//    }

    public String getBusName() {
        return busName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setAssignedBus(int assignedBus) {
        this.assignedBus = assignedBus;
    }

    /////////////////////////////////
    private void setDriverNameByID(int assignedBus) {
//        this.driverName = (assignedBus == 0) ? "Admin" : DAOBus.getDriverNameByID(assignedBus);
        this.driverName = DAOBus.getDriverNameByID(assignedBus);
    }
    private void setBusNameByID(int busID) {
        this.busName =DAOBus.getBusNameByID(busID);
    }
    ///////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return routeID == route.routeID &&
                assignedBus == route.assignedBus &&
                Objects.equals(routeName, route.routeName) &&
                Objects.equals(driverName, route.driverName) &&
                Objects.equals(busName, route.busName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(routeID, routeName, assignedBus, driverName, busName);
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + routeID +
                ", routeName='" + routeName + '\'' +
                ", assignedBus=" + assignedBus +
                ", driverName='" + driverName + '\'' +
                ", busName='" + busName + '\'' +
                '}';
    }

}
