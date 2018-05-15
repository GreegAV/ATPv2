package entities;

import java.util.Objects;

public class Bus {
    private int busID;
    private String busName;
    private int driverID;
    private int routeID;

    public Bus(int busID, String busName, int driverID, int routeID) {
        this.busID = busID;
        this.busName = busName;
        this.driverID = driverID;
        this.routeID = routeID;
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
    public String toString() {
        return "Bus{" +
                "busID=" + busID +
                ", busName='" + busName + '\'' +
                ", driverID=" + driverID +
                ", routeID=" + routeID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return busID == bus.busID &&
                driverID == bus.driverID &&
                routeID == bus.routeID &&
                Objects.equals(busName, bus.busName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(busID, busName, driverID, routeID);
    }
}
