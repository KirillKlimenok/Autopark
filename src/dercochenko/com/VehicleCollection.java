package dercochenko.com;

import dercochenko.com.Vehicle.Engine.DieselEngine;
import dercochenko.com.Vehicle.Engine.ElectricalEngine;
import dercochenko.com.Vehicle.Engine.GasolineEngine;
import dercochenko.com.Vehicle.Engine.Startable;
import dercochenko.com.Vehicle.NotVehicleException;
import dercochenko.com.Vehicle.Rent;
import dercochenko.com.Vehicle.Vehicle;
import dercochenko.com.Vehicle.VehicleType;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VehicleCollection {
    private List<VehicleType> vehicleTypeList = new ArrayList<>();
    private List<Vehicle> vehicleList = new ArrayList<>();
    private final String vehiclesType;
    private final String vehicle;
    private final String rent;


    public VehicleCollection(String vehiclesType, String vehicle, String rent) {
        this.vehiclesType = vehiclesType;
        this.vehicle = vehicle;
        this.rent = rent;
    }

    public void init() {
        loadTypes(vehiclesType);
        Vehicle.setRentList(loadRents(rent));
        loadVehicle(vehicle);
    }


    public void display() {
        vehicleList.forEach(System.out::println);
    }

    public void sort(Comparator<Vehicle> comparator) {
        vehicleList.sort(comparator);
    }

    public int delete(int index) {
        if (index < 0 || index > vehicleList.size()) {
            return -1;
        } else {
            File file = new File(vehicle);

            try {
                Scanner scanner = new Scanner(file);
                ArrayList<String> vehicle = new ArrayList<>();

                while (scanner.hasNextLine()) {
                    vehicle.add(scanner.nextLine());
                }

                vehicle.remove(index);

                FileWriter fileWriter = new FileWriter(file, false);

                for (String s : vehicle) {
                    fileWriter.append(s).append("\n");
                }

                fileWriter.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            return index;
        }
    }

    private void loadTypes(String filename) {
        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                addNewVehicleType(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        VehicleType.addVehicleTypeInList(vehicleTypeList);
    }

    public void insert(int index, Vehicle vehicle) {
        String fileName = "D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\vehicles.csv";
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            ArrayList<String> vehicleFile = new ArrayList<>();

            if (index < 0) {
                while (scanner.hasNextLine()) {
                    vehicleFile.add(scanner.nextLine());
                }
                vehicleFile.add(vehicle.toString());
            } else {
                for (int i = 0; scanner.hasNextLine(); i++) {
                    if (i == index) {
                        vehicleFile.add(vehicle.toString());
                        vehicleFile.add(scanner.nextLine());
                    } else {
                        vehicleFile.add(scanner.nextLine());
                    }
                }
            }


            FileWriter fileWriter = new FileWriter(file, false);

            for (String s : vehicleFile) {
                fileWriter.write(s);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private List<Rent> loadRents(String filename) {
        File file = new File(filename);

        try {
            List<Rent> rentList = new ArrayList<>();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                addNewRent(scanner.nextLine(), rentList);
            }
            return rentList;
        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void loadVehicle(String fileName) {
        vehicleList.clear();
        Vehicle.resetCounter();

        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                createVehicle(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createVehicle(String line) {
        ArrayList<String> vehicle = new ArrayList<>(Arrays.asList(line.split(",")));
        for (String s :
                vehicle) {
            s = s.replace(' ', '\0');
        }
        try {
            VehicleType vehicleType = createType(vehicle.get(1));
            Startable engine = getEngine(vehicle.get(8), vehicle.get(9), vehicle.get(10));
            String modelCar = vehicle.get(2);
            String numberCar = vehicle.get(3);
            double weight = Double.parseDouble(vehicle.get(4).replace('"', '\n'));
            int manufactureYear = Integer.parseInt(vehicle.get(5));
            double mileage = Double.parseDouble(vehicle.get(6));
            String color = vehicle.get(7);

            vehicleList.add(new Vehicle(vehicleType, engine, modelCar, numberCar, weight, manufactureYear, mileage, color));
        } catch (NotVehicleException e) {
            delete(vehicleList.size());
            System.out.println(e.getMessage());
        }
    }

    private VehicleType createType(String str) {
        return vehicleTypeList.get(Integer.parseInt(str) - 1);
    }

    private Startable getEngine(String type, String fuelTankCapacity, String fuelConsumptionPer100) {
        switch (type) {
            case "Electrical" -> {
                return new ElectricalEngine(Double.parseDouble(fuelTankCapacity.replace('"', '\n')), Double.parseDouble(fuelConsumptionPer100));
            }
            case "Diesel" -> {
                return new DieselEngine(Double.parseDouble(fuelTankCapacity.replace('"', '\n')), Double.parseDouble(fuelConsumptionPer100));
            }
            case "Gasoline" -> {
                return new GasolineEngine(Double.parseDouble(fuelTankCapacity.replace('"', '\n')), Double.parseDouble(fuelConsumptionPer100));
            }
            default -> {
                return null;
            }
        }

    }

    private void addNewRent(String line, List<Rent> rent) throws ParseException {
        int id;
        Date date;
        float price;
        String[] strings = line.split(",");
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");

        id = Integer.parseInt(strings[0]);
        date = format.parse(strings[1]);
        price = Float.parseFloat(strings[2].replace('"', '\n'));
        rent.add(new Rent(id, date, price));
    }


    private void addNewVehicleType(String line) {
        String[] vehicleType;
        String typeName;
        double taxCoefficient;

        vehicleType = line.split(",");

        typeName = vehicleType[1];
        taxCoefficient = Double.parseDouble(vehicleType[2].replace('"', '\n'));

        vehicleTypeList.add(new VehicleType(typeName, taxCoefficient));
    }

    public List<VehicleType> getVehicleTypes() {
        return new ArrayList<>(vehicleTypeList);
    }

    public List<Vehicle> getVehicleList() {
        return new ArrayList<>(vehicleList);
    }

    public Vehicle getVehicle(int id) {
        return vehicleList.get(id - 1);
    }
}
