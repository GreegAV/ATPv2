package entities;

import dao.DAOBus;
import dao.DAODriver;

public class Route {
    private int routeID;
    private String routeName;
    private int assignedBus;

    private int driverID;
    private String driverName;
    private String busName;

    public Route(int routeID, String routeName,  int assignedBus) {
        this.routeID = routeID;
        this.routeName = routeName;
        this.assignedBus=assignedBus;
        setDriverID(routeID);
        setDriverNameByID(driverID);
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

    public int getDriverID() {
        return driverID;
    }

    public String getBusName() {
        return busName;
    }

    public void setDriverNameByID(int driverID) {
        this.driverName=DAODriver.getDriverNameByID(driverID);
    }

    /////////////////////////////////
    public String getDriverName() {
        return driverName;
    }

    public void setDriverID(int routeID) {
        this.driverID = DAODriver.getDriverIDByRouteID(routeID);
    }

    public void setBusNameByID(int busID) {
        this.busName = DAOBus.getBusNameByID(busID);
    }
///////////////////////////////




}
