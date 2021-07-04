package dercochenko.com.Vehicle;

import java.util.NoSuchElementException;

public class VehicleStack {
    public Vehicle[] vehicles = new Vehicle[10];
    private int counter = 0;

    public VehicleStack() {
    }

    public void push(Vehicle vehicle) {
        if (counter > vehicles.length) {
            vehicles = getNewLength(vehicles);
        }
        System.arraycopy(vehicles, 0, vehicles, 1, vehicles.length-1);
        vehicles[0] = vehicle;
        counter++;
    }

    public Vehicle pop() {
        if (vehicles[0] == null) {
            throw new NoSuchElementException();
        }

        Vehicle vehicle = vehicles[0];
        vehicles[0] = null;

        System.arraycopy(vehicles, 1, vehicles, 0, vehicles.length - 1);

        counter--;

        return vehicle;
    }

    public Vehicle peek() {
        if (vehicles[0] == null) {
            throw new NoSuchElementException();
        }
        return vehicles[0];
    }

    public int getCount() {
        return counter;
    }


    private Vehicle[] getNewLength(Vehicle[] vehicles) {
        Vehicle[] newVehicles = new Vehicle[(int) (vehicles.length * 1.5 + 1)];

        System.arraycopy(vehicles, 0, newVehicles, 0, vehicles.length);

        return newVehicles;
    }
}
