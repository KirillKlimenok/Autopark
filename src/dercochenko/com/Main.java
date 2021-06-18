package dercochenko.com;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        VehicleType.addVehicleTypeInList("Bus", 1.2);
        VehicleType.addVehicleTypeInList("Car", 1);
        VehicleType.addVehicleTypeInList("Rink", 1.5);
        VehicleType.addVehicleTypeInList("Tractor", 1.2);

        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(0), "Volkswagen Crafter", "5427 AX-7", 2022, 2015, 376000, "Blue", 60);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(0), "Volkswagen Crafter", "6427 AA-7", 2500, 2025, 227010, "White", 50);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(0), "Electric bus E321", "6785 BA-7", 12080, 2019, 20451, "Green", 80);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(1), "Golf 5", "8682 AX-7", 1200, 2016, 230451, "Grey", 55);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(1), "Tesla Model S 70D", "0001 AA-7", 2200, 2009, 10454, "White", 65);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(2), null, "1561 FF-8", 3000, 2016, 122, "Yellow", 70);
        Vehicle.addNewVehicle(VehicleType.vehicleTypes.get(3), "МТЗ Беларус-1025.4", "1145 AB-7", 1200, 2020, 109, "Red", 75);

        Vehicle.getVehicles().forEach(System.out::print);

        sortVehicle(Vehicle.getVehicles());

        System.out.println();

        Vehicle.getVehicles().forEach(System.out::print);

        System.out.println();

        printMaxAndMinMileage(Vehicle.getVehicles());
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
}
