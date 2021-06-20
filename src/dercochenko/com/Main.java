package dercochenko.com;

import dercochenko.com.Vehicle.Engine.DieselEngine;
import dercochenko.com.Vehicle.Engine.ElectricalEngine;
import dercochenko.com.Vehicle.Engine.GasolineEngine;
import dercochenko.com.Vehicle.Vehicle;
import dercochenko.com.Vehicle.VehicleType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        VehicleType.addVehicleTypeInList("Bus", 1.2);
        VehicleType.addVehicleTypeInList("Car", 1);
        VehicleType.addVehicleTypeInList("Rink", 1.5);
        VehicleType.addVehicleTypeInList("Tractor", 1.2);

        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(0), new GasolineEngine(75, 8.1), null, "5427 AX-7", 2022, 2015, 376000, "Blue", 60);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(0), new GasolineEngine(75, 8.1), "Volkswagen Crafter", "6427 AA-7", 2500, 2025, 227010, "White", 50);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(0), new ElectricalEngine(150, 50), "Electric bus E321", "6785 BA-7", 12080, 2019, 20451, "Green", 80);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(1), new DieselEngine(55, 7.2), "Golf 5", "8682 AX-7", 1200, 2016, 230451, "Grey", 55);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(1), new ElectricalEngine(70, 25), "Tesla Model S 70D", "0001 AA-7", 2200, 2009, 10454, "White", 65);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(2), new DieselEngine(25, 3.2), "Hamm HD 12 VV", "1561 FF-8", 3000, 2016, 122, "Yellow", 70);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(3), new DieselEngine(135, 4.75), "МТЗ Беларус-1025.4", "1145 AB-7", 1200, 2020, 109, "Red", 75);

        Vehicle.getVehicles().forEach(System.out::print);

        sortVehicle(Vehicle.getVehicles());

        System.out.println();

        Vehicle.getVehicles().forEach(System.out::print);

        System.out.println();

        printMaxAndMinMileage(Vehicle.getVehicles());

        printEqualVehicle(Vehicle.getVehicles());

        System.out.println(getVehicleWithMaxLengthKm(Vehicle.getVehicles()));

    }

    public static void sortVehicle(ArrayList<Vehicle> vehicles) { // O = n^2
        for (int i = 0; i < vehicles.size(); i++) {
            for (int j = 0; j < vehicles.size() - 1; j++) {
                if (vehicles.get(j).compareTo(vehicles.get(j + 1)) < 0) {
                    Vehicle vehicle = vehicles.get(j);
                    Vehicle.setVehiclesForId(j, vehicles.get(j + 1));
                    Vehicle.setVehiclesForId(j + 1, vehicle);

                    vehicles = Vehicle.getVehicles();
                }
            }
        }
    }

    public static void printMaxAndMinMileage(ArrayList<Vehicle> vehicles) {
        Vehicle maxMileage = vehicles.get(0);
        Vehicle minMileage = vehicles.get(0);

        for (int i = 1; i < vehicles.size(); i++) {
            if (maxMileage.getMileage() < vehicles.get(i).getMileage()) maxMileage = vehicles.get(i);

            if (minMileage.getMileage() > vehicles.get(i).getMileage()) minMileage = vehicles.get(i);
        }

        System.out.println("Car With max mileage: " + maxMileage.toString());
        System.out.println("Car With min mileage: " + minMileage.toString());
    }

    public static void printEqualVehicle(ArrayList<Vehicle> vehicles) {
        ArrayList<Vehicle> equalsVehicle = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            equalsVehicle.add(vehicles.get(i));
            for (int j = i + 1; j < vehicles.size(); j++) {
                if (vehicles.get(i).equals(vehicles.get(j))) {
                    equalsVehicle.add(vehicles.get(i));
                }
            }
            if (equalsVehicle.size() > 1) System.out.println(equalsVehicle);
            equalsVehicle.clear();
        }
    }

    public static Vehicle getVehicleWithMaxLengthKm(ArrayList<Vehicle> vehicles) {
        Vehicle vehicle = vehicles.get(0);
        for (Vehicle value : vehicles) {
            if (value.getEngine().getMaxKilometers() > vehicle.getEngine().getMaxKilometers()) {
                vehicle = value;
            }
        }
        return vehicle;
    }
}
