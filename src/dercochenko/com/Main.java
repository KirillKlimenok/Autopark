package dercochenko.com;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        VehicleType[] types = new VehicleType[]{
                new VehicleType("Bus", 1.2),
                new VehicleType("Car", 1),
                new VehicleType("Rink", 1.5),
                new VehicleType("Tractor", 1.2)};

        Vehicle[] vehicles = new Vehicle[]{
                new Vehicle(types[0], "Volkswagen Crafter", "5427 AX-7", 2022, "2015", 376000, Vehicle.Color.Blue, 60),
                new Vehicle(types[0], "Volkswagen Crafter", "6427 AA-7", 2500, "2014", 227010, Vehicle.Color.White, 50),
                new Vehicle(types[0], "Electric bus E321", "6785 BA-7", 12080, "2019", 20451, Vehicle.Color.Green, 80),
                new Vehicle(types[1], "Golf 5", "8682 AX-7", 1200, "2006", 230451, Vehicle.Color.Grey, 55),
                new Vehicle(types[1], "Tesla Model S 70D", "E001 AA-7", 2200, "2019", 10454, Vehicle.Color.White, 65),
                new Vehicle(types[2], "Hamm HD 12 VV", null, 3000, "2016", 122, Vehicle.Color.Yellow, 70),
                new Vehicle(types[3], "МТЗ Беларус-1025.4", "1145 AB-7", 1200, "2020", 109, Vehicle.Color.Red, 75),
        };

        printAllVehicle(vehicles);

        sortVehicle(vehicles);
        System.out.println();

        printAllVehicle(vehicles);

        System.out.println();

        printMaxAndMinMileage(vehicles);
    }

    public static void printAllVehicle(Vehicle[] vehicles) {
        System.out.println(Arrays.toString(vehicles));
    }

    public static void sortVehicle(Vehicle[] vehicles) { // O = n^2
        for (int j = 0; j < vehicles.length; j++)
            for (int i = 0; i < vehicles.length - 1; i++) {
                Vehicle vehicle;
                if (vehicles[i].compareTo(vehicles[i + 1]) < 0) {
                    vehicle = vehicles[i];
                    vehicles[i] = vehicles[i + 1];
                    vehicles[i + 1] = vehicle;
                }
            }
    }

    public static void printMaxAndMinMileage(Vehicle[] vehicles) {
        Vehicle maxMileage = vehicles[0];
        Vehicle minMileage = vehicles[0];

        for (int i = 1; i < vehicles.length; i++) {
            if (maxMileage.getMileage() < vehicles[i].getMileage()) maxMileage = vehicles[i];

            if (minMileage.getMileage() > vehicles[i].getMileage()) minMileage = vehicles[i];
        }

        System.out.println("Car With max mileage: " + maxMileage.toString());
        System.out.println("Car With min mileage: " + minMileage.toString());
    }
}
