package entities;

import java.util.Objects;

public class Bus {
    private int busID;
    private String busName;
    private int driverID;

    public Bus(int busID, String busName, int driverID) {

        this.busID = busID;
        this.busName = busName;
        this.driverID = driverID;
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
                Objects.equals(busName, bus.busName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(busID, busName, driverID);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busID=" + busID +
                ", busName='" + busName + '\'' +
                ", driverID=" + driverID +
                '}';
    }
}
