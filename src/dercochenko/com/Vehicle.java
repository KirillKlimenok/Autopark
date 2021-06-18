package dercochenko.com;

import java.util.Objects;

public class Vehicle {
    private VehicleType vehicleType;
    private String modelCar;
    private String stateNumber;
    private double weight;
    private String manufactureYear;
    private double mileage;
    private Enum color;
    private double tankCapacity;

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

    public Vehicle(VehicleType vehicleType, String modelCar, String stateNumber, double weight, String manufactureYear, double mileage, Enum color, double tankCapacity) {
        this.vehicleType = vehicleType;
        this.modelCar = modelCar;
        this.stateNumber = stateNumber;
        this.weight = weight;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
        this.color = color;
        this.tankCapacity = tankCapacity;
    }

    public Vehicle() {
    }

    public double getCalcTaxPerMonth() {
        return (weight * 0.0013) + (vehicleType.getTaxCoefficient() * 30) + 5;
    }

    public int compareTo(Vehicle o) {
        if (manufactureYear.equals(o.manufactureYear)) {
            if (mileage == o.mileage) {
                return 0;
            } else return (int) (mileage - o.mileage);
        } else {
            return Integer.parseInt(manufactureYear) - Integer.parseInt(o.manufactureYear);
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
        return "Vehicle{" +
                "vehicleType=" + vehicleType +
                ", modelCar='" + modelCar + '\'' +
                ", stateNumber='" + stateNumber + '\'' +
                ", weight=" + weight +
                ", manufactureYear='" + manufactureYear + '\'' +
                ", mileage=" + mileage +
                ", color=" + color +
                ", tankCapacity=" + tankCapacity +
                '}';
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public Enum getColor() {
        return color;
    }

    public void setColor(Enum color) {
        this.color = color;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
}
