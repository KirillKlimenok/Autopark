package dercochenko.com.Garage;

import dercochenko.com.Vehicle.Vehicle;
import dercochenko.com.Vehicle.VehicleStack;

import java.util.List;

public class CarWash {
    private static VehicleStack vehicleStack = new VehicleStack();

    public static void getClearVehicle(List<Vehicle> vehicleList) {
        vehicleList.stream().filter(Vehicle::isClean).forEach(x -> System.out.println("Vehicle" + x.getId() + " is clear"));

        vehicleList.stream().filter(x -> !x.isClean()).forEach(x -> {
            x.setClean(true);
            System.out.println("Vehicle" + x.getId() + " washed!");
        });

    }
}
