package dercochenko.com;

import dercochenko.com.Garage.Garage;
import dercochenko.com.Garage.MechanicService;
import dercochenko.com.Vehicle.DefectedVehicleException;
import dercochenko.com.Vehicle.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        init();

        VehicleCollection vehicleCollection = new VehicleCollection("D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\types.csv", "D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\vehicles.csv", "D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\rents.csv");
        MechanicService myMechanic = new MechanicService();

        vehicleCollection.init();

        Garage myGarage = new Garage();

        myGarage.fullingGarage(vehicleCollection.getVehicleList());

        for (Vehicle v :
                vehicleCollection.getVehicleList()) {
            if (myMechanic.detectAndRepair(v)) {
                System.out.println("The car" + v.getId() + " is repaired");
            } else {
                System.out.println("The car" + v.getId() + " was not damaged");
            }
        }

        int maxCountDetect = 0;
        int idVehicle = 0;

        for (Vehicle v : vehicleCollection.getVehicleList()) {
            if (maxCountDetect < v.getCountDetected()) {
                maxCountDetect = v.getCountDetected();
                idVehicle = v.getId();
            }
        }

        System.out.println("Vehicle" + idVehicle + " has detected: " + maxCountDetect);

        myGarage.garageRelease();

        System.out.println("Do you want rent Auto" +
                "\nthe cost of renting any car is 50$");


        if (new Scanner(System.in).nextBoolean()) {
            vehicleCollection.display();
            RentAuto autoPark1 = new RentAuto(myMechanic);

            System.out.println("enter vehicle: ");
            try {
                myMechanic.detectBreaking(vehicleCollection.getVehicle(2));
                myMechanic.detectBreaking(vehicleCollection.getVehicle(3));
                myMechanic.detectBreaking(vehicleCollection.getVehicle(4));

                Vehicle v = vehicleCollection.getVehicle(new Scanner(System.in).nextInt());
                System.out.println(v);
                if (autoPark1.rentAuto(v, 50)) {
                    System.out.println("successful");
                }
            } catch (DefectedVehicleException e) {
                System.out.println(e.getMessage());
            }

        }

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
