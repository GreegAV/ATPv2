package entities;

public class Driver {
    private int userID;
    private String driverName;
    private int routeID;

    public Driver(int userID, String driverName, int routeID) {
        this.userID = userID;
        this.driverName = driverName;
        this.routeID = routeID;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverName='" + driverName + '\'' +
                ", routeID=" + routeID +
                '}';
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
}
