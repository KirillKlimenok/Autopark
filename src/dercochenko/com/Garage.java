package dercochenko.com;

import dercochenko.com.Vehicle.Vehicle;
import dercochenko.com.Vehicle.VehicleStack;

import java.util.List;

public class Garage {
    VehicleStack vehicleStack = new VehicleStack();

    public void fullingGarage(List<Vehicle> vehicles) {
        for (Vehicle v :
                vehicles) {
            System.out.println("Auto" + v.getId() + " drove into the garage");
            vehicleStack.push(v);
        }
        System.out.println("The garage is full!");
    }

    public void garageRelease() {
        for (int i = 0; i < vehicleStack.getCount(); i++) {
            System.out.println("Auto" + vehicleStack.pop().getId() + " left the garage");
            i--;
        }
    }


}
