package dercochenko.com.Vehicle;

import dercochenko.com.Garage.MechanicService;
import dercochenko.com.Vehicle.Engine.AbstractEngine;
import dercochenko.com.Vehicle.Engine.Startable;

import java.util.*;

import static dercochenko.com.Garage.TechnicalSpecialist.*;

public class Vehicle {
    private final VehicleType vehicleType;
    private final String modelCar;
    private String stateNumber;
    private double weight;
    private final int manufactureYear;
    private double mileage;
    private Enum<Color> color;
    private final Startable engine;
    private static int vehicleId = 1;
    private final int id;
    private static List<Rent> rentList;
    public static final Comparator<Vehicle> VEHICLE_COMPARATOR = Comparator.comparing(Vehicle::getModelCar);
    public static final Comparator<Vehicle> VEHICLE_COMPARATOR_BY_TAX_PER_MONTH = Comparator.comparing(Vehicle::getCalcTaxPerMonth);
    private Map<String, Integer> vehicleDetailsBreaking;
    private boolean isClean = true;

    public static boolean setRentList(List<Rent> rent) {
        if (rent != null) {
            rentList = rent;
            return true;
        } else {
            return false;
        }

    }

    public Vehicle(VehicleType vehicleType, Startable startable, String modelCar, String stateNumber, double weight, int manufactureYear, double mileage, String color) throws NotVehicleException {
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
            this.id = vehicleId++;
            this.engine = startable;
            this.vehicleType = vehicleType;
            this.modelCar = modelCar;
            this.stateNumber = stateNumber;
            this.weight = weight;
            this.manufactureYear = manufactureYear;
            this.mileage = mileage;
            this.color = Color.valueOf(color);
        }
    }

    public static void resetCounter() {
        vehicleId = 1;
    }

    public double getTotalIncome() {
        double sum = 0;
        for (int i = 0; i < rentList.size(); i++) {
            if (rentList.get(i).getIdVehicle() == id) {
                sum += rentList.get(i).getRentalPrice();
            }
        }
        return sum;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public double getTotalProfit() {
        return getTotalIncome() - getCalcTaxPerMonth();
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
        return id +
                "    " + vehicleType +
                "    " + modelCar +
                "    " + stateNumber +
                "    " + weight +
                "    " + manufactureYear +
                "    " + mileage +
                "    " + color +
                "    " + getTotalIncome() +
                "    " + getCalcTaxPerMonth() +
                "    " + getTotalProfit() + '\n';
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

    public int getCountDetected() {
        int countDetected = 0;
        for (int i = 0; i < MechanicService.details.length; i++) {
            try {
                countDetected += vehicleDetailsBreaking.get(MechanicService.details[i]);
            } catch (NullPointerException ignored) {

            }
        }
        return countDetected;
    }

    public Map<String, Integer> getVehicleDetailsBreaking() {
        return vehicleDetailsBreaking;
    }

    public void setVehicleDetailsBreaking(Map<String, Integer> vehicleDetailsBreaking) {
        if (vehicleDetailsBreaking != null)
            this.vehicleDetailsBreaking = vehicleDetailsBreaking;
    }

    public int getId() {
        return id;
    }
}
