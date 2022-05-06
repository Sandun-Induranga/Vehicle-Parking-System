package model;


public class Bus extends Vehicle {
    public static int[] busSlots = {14};

    public Bus(String vehicleNum, VehicleType vehicleType, int maxWeight, int numOfPassengers) {
        super(vehicleNum, vehicleType, maxWeight, numOfPassengers);
    }

    public Bus() {
        super();
    }

    @Override
    public void park(String vehicleNum) {
        setPark(vehicleNum);
    }

    @Override
    public void leavePark(String vehicleNum, String driver) {
        setDelivery(vehicleNum, driver, busSlots, VehicleType.BUS);
    }
}
