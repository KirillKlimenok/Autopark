package dercochenko.com;

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

    enum Color {
        Red("Red"),
        Grey("Grey"),
        LightBlue("Light Blue"),
        Blue("Blue"),
        Green("Green"),
        Yellow("Yellow"),
        Pink("Pink"),
        Orange("Orange"),
        Brown("Brown"),
        White("White"),
        Black("Black"),
        Violet("Violet");

        private final String color;

        Color(String color) {
            this.color = color;
        }

        public String getColor() {
            return this.color;
        }

    }

    public static void addNewVehicle(VehicleType vehicleType, String modelCar, String stateNumber, double weight, int manufactureYear, double mileage, String color, double tankCapacity) {
        try {
            vehicles.add(new Vehicle(vehicleType, modelCar, stateNumber, weight, manufactureYear, mileage, color, tankCapacity));
        } catch (ExceptionInInitializerError | IllegalArgumentException e) {
            System.out.println("You enter incorrect auto, this auto don't created !");
        }
    }

    public Vehicle(VehicleType vehicleType, String modelCar, String stateNumber, double weight, int manufactureYear, double mileage, String color, double tankCapacity) {
        if (!validateVehicleType(vehicleType.getTypeName())) {
            System.out.println("Incorrect type vehicle!");
            throw new ExceptionInInitializerError();
        } else if (!validateModelName(modelCar)) {
            System.out.println("Incorrect model name!");
            throw new ExceptionInInitializerError();
        } else if (!validateRegistrationNumber(stateNumber)) {
            System.out.println("Incorrect state number!");
            throw new ExceptionInInitializerError();
        } else if (!validateWeight(weight)) {
            System.out.println("Incorrect weight!");
            throw new ExceptionInInitializerError();
        } else if (!validateManufactureYear(manufactureYear)) {
            System.out.println("Incorrect manufacture error!");
            throw new ExceptionInInitializerError();
        } else if (!validateMileage(mileage)) {
            System.out.println("Incorrect mileage!");
            throw new ExceptionInInitializerError();
        } else if (!validateColorString(color)) {
            System.out.println("Incorrect color!");
            throw new ExceptionInInitializerError();
        } else {
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
        return (weight * 0.0013) + (vehicleType.getTaxCoefficient() * 30) + 5;
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
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicleType.equals(vehicle.vehicleType) && modelCar.equals(vehicle.modelCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, modelCar);
    }

    @Override
    public String toString() {
        if (vehicleType == null) {
            return "Incorrect auto";
        }
        return vehicleType +
                ", " + modelCar +
                ", " + stateNumber +
                ", " + weight + " kg" +
                ", " + manufactureYear +
                ", " + mileage + " km" +
                ", " + color +
                ", " + tankCapacity + "l" + '\n';
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
        return (ArrayList<Vehicle>) vehicles.clone();
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
        if(tankCapacity > 0) this.tankCapacity = tankCapacity;
        else System.out.println("Incorrect data, you entered incorrect tank capacity");
    }
}
