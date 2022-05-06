package model;

import Db.InParkingDb;
import Db.OnDeliveryDb;
import Db.SlotDb;
import Db.VehicleDb;
import controllers.MainFormController;
import tm.InParkingTm;
import tm.OnDeliveryTm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Vehicle {
    private String VehicleNum;
    private VehicleType vehicleType;
    private int maxWeight;
    private int numOfPassengers;

    public Vehicle(String vehicleNum, VehicleType vehicleType, int maxWeight, int numOfPassengers) {
        VehicleNum = vehicleNum;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        this.numOfPassengers = numOfPassengers;
    }

    public Vehicle() {
    }

    public abstract void park(String vehicleNum);

    public abstract void leavePark(String vehicleNum, String driver);

    public void setPark(String vehicleNum) {
        for (Vehicle v :
                VehicleDb.vehicles) {
            if (v.getVehicleNum().equals(vehicleNum)) {
                SlotDb.vehicleArray[MainFormController.slotNum - 1] = v;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
                InParkingDb.parkingData.add(new InParkingTm(v.getVehicleNum(), v.getVehicleType().toString(), MainFormController.slotNum, dtf.format(LocalDateTime.now())));
            }
        }
    }

    public void setDelivery(String vehicleNum, String driver, int[] slots, VehicleType type) {
        for (int x :
                slots) {
            if (SlotDb.vehicleArray[x - 1] != null && vehicleNum.equals(SlotDb.vehicleArray[x - 1].getVehicleNum())) {
                SlotDb.vehicleArray[x - 1] = null;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
                OnDeliveryTm tm = new OnDeliveryTm(vehicleNum, type.toString(), driver, dtf.format(LocalDateTime.now()));
                OnDeliveryDb.onDeliveryData.add(tm);
                break;
            }
        }
    }


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
}
