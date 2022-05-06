package model;

import controllers.MainFormController;


public class Lorry extends Vehicle {
    public static int[] lorrySlots = {5, 6, 7, 8, 9, 10, 11};

    public Lorry(String vehicleNum, VehicleType vehicleType, int maxWeight, int numOfPassengers) {
        super(vehicleNum, vehicleType, maxWeight, numOfPassengers);
    }

    public Lorry() {
        super();
    }

    @Override
    public void park(String vehicleNum) {
        setPark(vehicleNum);
    }

    @Override
    public void leavePark(String vehicleNum, String driver) {
        setDelivery(vehicleNum, driver, lorrySlots, VehicleType.LORRY);
    }

}
