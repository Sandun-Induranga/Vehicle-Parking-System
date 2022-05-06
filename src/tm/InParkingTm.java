package tm;


public class InParkingTm {
    private String vehicleNum;
    private String type;
    private int slotNum;
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

    public int getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public InParkingTm(String vehicleNum, String type, int slotNum, String time) {
        this.vehicleNum = vehicleNum;
        this.type = type;
        this.slotNum = slotNum;
        this.time = time;
    }
}
