package dercochenko.com.Vehicle;

import java.util.NoSuchElementException;

public class VehicleArrayDeque {
    public Vehicle[] vehicles = new Vehicle[10];
    private int counter = 0;

    public VehicleArrayDeque() {
    }

    public void addLast(Vehicle vehicle) {
        if (counter < vehicles.length) {
            vehicles[counter] = vehicle;
            counter++;
        } else {
            vehicles = getNewLength(vehicles);
            vehicles[counter] = vehicle;
        }
    }

    public void addFirst(Vehicle vehicle) {
        if (counter > vehicles.length - 1) {
            vehicles = getNewLength(vehicles);
        }
        System.arraycopy(vehicles, 0, vehicles, 1, vehicles.length);
        vehicles[0] = vehicle;
        counter++;
    }

    public Vehicle getFirst() {
        if (vehicles[0] != null) {
            return vehicles[0];
        } else {
            throw new NoSuchElementException("");
        }
    }

    public Vehicle getLast() {
        if (vehicles[0] != null) {
            Vehicle vehicle = vehicles[counter - 1];
            counter--;
            return vehicle;
        } else {
            throw new NoSuchElementException("");
        }
    }

    public boolean offerFirst(Vehicle vehicle) {
        try {
            addFirst(vehicle);
            return true;
        } catch (OutOfMemoryError e) {
            return false;
        }
    }

    public boolean offerLast(Vehicle vehicle) {
        try {
            addLast(vehicle);
            return true;
        } catch (OutOfMemoryError e) {
            return false;
        }
    }

    public Vehicle peekFirst() {
        try {
            return getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Vehicle peekLast() {
        try {
            return getLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Vehicle pollFirst() {
        Vehicle vehicle = getFirst();

        if (vehicle == null) {
            return null;
        }

        vehicles[0] = null;

        System.arraycopy(vehicles, 1, vehicles, 0, vehicles.length - 1);

        return vehicle;
    }

    public Vehicle pollLast() {
        Vehicle vehicle = getLast();

        if (vehicle == null) {
            return null;
        }

        vehicles[counter - 1] = null;

        counter--;

        return vehicle;
    }

    public Vehicle pop() {
        Vehicle vehicle = pollFirst();

        if (vehicle == null) {
            throw new NoSuchElementException();
        } else {
            return vehicle;
        }
    }

    public void push(Vehicle vehicle) {
        addFirst(vehicle);
    }

    public Vehicle removeFirst() {
        Vehicle vehicle = pop();
        if (vehicle != null) {
            return vehicle;
        } else {
            throw new NoSuchElementException();
        }
    }

    public Vehicle removeLast() {
        Vehicle vehicle = pollLast();
        if (vehicle != null) {
            return vehicle;
        } else {
            throw new NoSuchElementException();
        }
    }

    boolean removeFirstOccurrence(Vehicle vehicle) {
        if (vehicle.equals(getFirst())) {
            removeFirst();
            return true;
        } else {
            return false;
        }
    }

    boolean removeLastOccurrence(Vehicle vehicle) {
        if (vehicle.equals(getLast())) {
            removeLast();
            return true;
        } else {
            return false;
        }
    }

    public int getCounter() {
        return counter;
    }

    private Vehicle[] getNewLength(Vehicle[] vehicles) {
        Vehicle[] newVehicles = new Vehicle[(int) (vehicles.length * 1.5 + 1)];

        System.arraycopy(vehicles, 0, newVehicles, 0, vehicles.length);

        return newVehicles;
    }

}
