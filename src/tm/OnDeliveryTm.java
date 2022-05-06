package tm;

public class OnDeliveryTm {
    private String vehicleNum;
    private String type;
    private String driverName;
    private String time;

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OnDeliveryTm(String vehicleNum, String type, String driverName, String time) {
        this.vehicleNum = vehicleNum;
        this.type = type;
        this.driverName = driverName;
        this.time = time;
    }
}
