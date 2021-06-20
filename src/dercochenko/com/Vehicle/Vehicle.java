package dercochenko.com.Vehicle;

import dercochenko.com.Vehicle.Engine.AbstractEngine;
import dercochenko.com.Vehicle.Engine.Startable;

import java.util.ArrayList;
import java.util.Objects;

import static dercochenko.com.TechnicalSpecialist.*;

public class Vehicle {
    private final VehicleType vehicleType;
    private final String modelCar;
    private String stateNumber;
    private double weight;
    private final int manufactureYear;
    private double mileage;
    private Enum<Color> color;
    private double tankCapacity;
    private final static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final Startable engine;

    public static void addNewVehicle(VehicleType vehicleType, Startable startable, String modelCar, String stateNumber, double weight, int manufactureYear, double mileage, String color, double tankCapacity) {
        try {
            vehicles.add(new Vehicle(vehicleType, startable, modelCar, stateNumber, weight, manufactureYear, mileage, color, tankCapacity));
        } catch (NotVehicleException e) {
            System.out.println(e.getMessage() + " You enter incorrect auto, this auto don't created !");
        }
    }

    private Vehicle(VehicleType vehicleType, Startable startable, String modelCar, String stateNumber, double weight, int manufactureYear, double mileage, String color, double tankCapacity) throws NotVehicleException {
        if (!validateVehicleType(vehicleType.getTypeName())) {
            throw new NotVehicleException("Incorrect type vehicle!");
        } else if (!validateModelName(modelCar)) {
            throw new NotVehicleException("Incorrect model name!");
        } else if (!validateRegistrationNumber(stateNumber)) {
            throw new NotVehicleException("Incorrect state number!");
        } else if (!validateWeight(weight)) {
            throw new NotVehicleException("Incorrect weight!");
        } else if (!validateManufactureYear(manufactureYear)) {
            throw new NotVehicleException("Incorrect manufacture error!");
        } else if (!validateMileage(mileage)) {
            throw new NotVehicleException("Incorrect mileage!");
        } else if (!validateColorString(color)) {
            throw new NotVehicleException("Incorrect color!");
        } else if (!validateEngine(startable)) {
            throw new NotVehicleException("Incorrect engine!");
        } else {
            this.engine = startable;
            this.vehicleType = vehicleType;
            this.modelCar = modelCar;
            this.stateNumber = stateNumber;
            this.weight = weight;
            this.manufactureYear = manufactureYear;
            this.mileage = mileage;
            this.color = Color.valueOf(color);
            this.tankCapacity = tankCapacity;
        }
    }

    public double getCalcTaxPerMonth() {
        return (weight * 0.0013) + (vehicleType.getTaxCoefficient() * engine.getTaxPerMonth() * 30) + 5;
    }

    public int compareTo(Vehicle o) {
        if (manufactureYear == o.manufactureYear) {
            if (mileage == o.mileage) {
                return 0;
            } else return (int) (mileage - o.mileage);
        } else {
            return manufactureYear - o.manufactureYear;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o instanceof AbstractEngine) return false;
        Vehicle vehicle = (Vehicle) o;
        boolean isEqualsEngine = engine.equals(((Vehicle) o).engine);
        return vehicleType.equals(vehicle.vehicleType) && modelCar.equals(vehicle.modelCar) && isEqualsEngine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, modelCar, engine);
    }

    @Override
    public String toString() {
        if (vehicleType == null) {
            return "Incorrect auto";
        }
        return vehicleType +
                "," + engine +
                ", maxKm: " +
                engine.getMaxKilometers() +
                ", " + modelCar +
                ", " + stateNumber +
                ", " + weight + " kg" +
                ", " + manufactureYear +
                ", " + mileage + " km" +
                ", " + color +
                ", " + tankCapacity + "l" + '\n';
    }

    public Startable getEngine() {
        return engine;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getModelCar() {
        return modelCar;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    //return clone List Vehicle
    public static ArrayList<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public static void setVehiclesForId(int id, Vehicle vehicle) {
        vehicles.set(id, vehicle);
    }

    public void setStateNumber(String stateNumber) {
        if (validateRegistrationNumber(stateNumber)) this.stateNumber = stateNumber;
        else System.out.println("incorrect data, you entered incorrect state number!");
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (validateWeight(weight)) this.weight = weight;
        else System.out.println("Incorrect data, you entered incorrect weight");
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        if (validateMileage(mileage)) this.mileage = mileage;
        else System.out.println("Incorrect data, you entered incorrect mileage");
    }

    public Enum<Color> getColor() {
        return color;
    }

    public void setColor(String color) {
        if (validateColorString(color)) this.color = Color.valueOf(color);
        else System.out.println("Incorrect data, you entered incorrect color");
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        if (tankCapacity > 0) this.tankCapacity = tankCapacity;
        else System.out.println("Incorrect data, you entered incorrect tank capacity");
    }
}
