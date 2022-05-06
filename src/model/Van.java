package model;


public class Van extends Vehicle {
    public static int[] vanSlots = {1, 2, 3, 4, 12, 13};

    public Van(String vehicleNum, VehicleType vehicleType, int maxWeight, int numOfPassengers) {
        super(vehicleNum, vehicleType, maxWeight, numOfPassengers);
    }

    public Van() {
        super();
    }

    @Override
    public void park(String vehicleNum) {
        setPark(vehicleNum);
    }

    @Override
    public void leavePark(String vehicleNum, String driver) {
        setDelivery(vehicleNum, driver, vanSlots, VehicleType.VAN);
    }
}
