package Db;

import model.*;

import java.util.ArrayList;

public class VehicleDb {
    public static ArrayList<Vehicle> vehicles = new ArrayList<>();

    static {
        vehicles.add(new Bus("NA-3434", VehicleType.BUS, 3500, 60));
        vehicles.add(new Van("KA-4563", VehicleType.VAN, 1000, 7));
        vehicles.add(new Van("58-3567", VehicleType.VAN, 1500, 4));
        vehicles.add(new Van("GF-4358", VehicleType.VAN, 800, 4));
        vehicles.add(new Van("CCB-3568", VehicleType.VAN, 1800, 8));
        vehicles.add(new Van("LM-6679", VehicleType.VAN, 1500, 4));
        vehicles.add(new Van("QA-3369", VehicleType.VAN, 1800, 6));
        vehicles.add(new Lorry("KB-3668", VehicleType.LORRY, 2500, 2));
        vehicles.add(new Lorry("JJ-9878", VehicleType.LORRY, 3000, 2));
        vehicles.add(new Lorry("GH-5772", VehicleType.LORRY, 4000, 3));
        vehicles.add(new Lorry("XY-4456", VehicleType.LORRY, 3500, 2));
        vehicles.add(new Lorry("YQ-3536", VehicleType.LORRY, 2000, 2));
        vehicles.add(new Lorry("CBB-3566", VehicleType.LORRY, 2500, 2));
        vehicles.add(new Lorry("QH-3444", VehicleType.LORRY, 5000, 4));
    }
}
