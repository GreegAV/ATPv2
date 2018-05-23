package entities;

import dao.DAOBus;
import dao.DAODriver;

import java.util.Objects;

public class Route {
    private int routeID;
    private String routeName;
    private int driverID;
    private int busID;
    private String driverName;
    private String busName;

    public Route(int routeID, String routeName,  int assignedBus) {
        this.routeID = routeID;
        this.routeName = routeName;
//        setDriverNameByID(assigned2Driver);
        setBusNameByID(assignedBus);
    }

    public Route(int routeID, String routeName, int driverID, int busID, String driverName, String busName) {
        this.routeID = routeID;
        this.routeName = routeName;
        this.driverID = driverID;
        this.busID = busID;
        this.driverName = driverName;
        this.busName = busName;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + routeID +
                ", routeName='" + routeName + '\'' +
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
                driverID == route.driverID &&
                busID == route.busID &&
                Objects.equals(routeName, route.routeName) &&
                Objects.equals(driverName, route.driverName) &&
                Objects.equals(busName, route.busName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(routeID, routeName, driverID, busID, driverName, busName);
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

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public void setBusNameByID(int busID) {
        this.busName = DAOBus.getBusNameByID(busID);
    }

    public void setDriverNameByID(int driverID) {
        this.driverName = DAODriver.getDriverNameByID(driverID);
    }


}
