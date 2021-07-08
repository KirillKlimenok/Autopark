package dercochenko.com;

import dercochenko.com.Garage.CarWash;
import dercochenko.com.Garage.MechanicService;
import dercochenko.com.Vehicle.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        init();

        VehicleCollection vehicleCollection = new VehicleCollection("D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\types.csv", "D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\vehicles.csv", "D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\rents.csv");
        MechanicService myMechanic = new MechanicService();
        vehicleCollection.init();

        List<Vehicle> vehicles = vehicleCollection.getVehicleList();
        vehicles.forEach(myMechanic::detectBreaking);
        vehicles.stream().filter(x -> x.getCountDetected() > 0).forEach(System.out::println);

        vehicles.stream().filter(x -> x.getCountDetected() > 0).collect(Collectors.toList()).stream().sorted(Comparator.comparing(Vehicle::getCountDetected)).forEach(x -> System.out.println("Vehicle (" + x.getId() + ") have detected: " + x.getCountDetected() + "\n"));
        //vehicles.forEach(myMechanic::repair);

        System.out.println("/".repeat(200) + "\n");

        List<Vehicle> max = vehicles.stream().max(Vehicle.VEHICLE_COMPARATOR_BY_TAX_PER_MONTH).stream().collect(Collectors.toList());
        max.forEach(System.out::println);

        System.out.println(vehicles.stream().filter(x -> x.getModelCar().split(" ")[0].equals("Volkswagen")).collect(Collectors.toList()).stream().max(Comparator.comparing(Vehicle::getManufactureYear)).get());

        CarWash.getClearVehicle(vehicles);

        System.out.println();

        vehicles.forEach(x -> {
            if (myMechanic.isBroken(x)) {
                System.out.println("Vehicle" + x.getId() + " have broken");
                myMechanic.repair(x);
            }
        });
    }

    //this method initializes the file with vehicle
    private static void init() {
        try (FileWriter fileWriter = new FileWriter("D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\vehicles.csv", false)) {
            fileWriter.append("""
                    1,1,Volkswagen Crafter,5427 AX-7,2022,2015,376000,Blue,Gasoline,"8.1",75
                    2,1,Volkswagen Crafter,6427 AA-7,2500,2014,227010,White,Gasoline,"8.5",75
                    3,1,Electric Bus E321,6785 BA-7,12080,2019,20451,Green,Electrical,50,150
                    4,2,Golf 5,8682 AX-7,1200,2006,230451,Violet,Diesel,"7.2",55
                    5,2,Tesla Model S 70D,0001 AA-7,2200,2019,10454,White,Electrical,25,70
                    6,3,Hamm HD 12 VV,,3000,2016,122,Yellow,Diesel,25,20
                    7,4,MT3 Беларус-1025.4,1145 AB-7,1200,2020,109,Red,Diesel,"20.1",135""");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double getTotalProfit(List<Vehicle> vehicles) {
        return vehicles.stream().mapToDouble(Vehicle::getTotalProfit).sum();
    }
}
