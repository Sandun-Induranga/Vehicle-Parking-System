package tm;

import javafx.scene.control.Button;
import model.VehicleType;

public class VehicleTm {
    private String VehicleNum;
    private VehicleType vehicleType;
    private int maxWeight;
    private int numOfPassengers;
    private Button btn;

    public String getVehicleNum() {
        return VehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        VehicleNum = vehicleNum;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public VehicleTm(String vehicleNum, VehicleType vehicleType, int maxWeight, int numOfPassengers, Button btn) {
        VehicleNum = vehicleNum;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.numOfPassengers = numOfPassengers;
        this.btn = btn;
    }
}
