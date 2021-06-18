package dercochenko.com.Vehicle;

import java.util.ArrayList;

public class VehicleType {
    private String typeName;
    private double taxCoefficient;
    public static ArrayList<VehicleType> vehicleTypes = new ArrayList<>();

    public VehicleType(String typeName, double taxCoefficient) {
        this.typeName = typeName;
        this.taxCoefficient = taxCoefficient;
    }

    public static void addVehicleTypeInList(String typeName, double taxCoefficient) {
        if (taxCoefficient >= 0 && typeName != null) {
            vehicleTypes.add(new VehicleType(typeName, taxCoefficient));
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTaxCoefficient(double taxCoefficient) {
        this.taxCoefficient = taxCoefficient;
    }

    public void display() {
        System.out.println("typeName = " + typeName + "\n" + "taxCoefficient = " + taxCoefficient);
    }

    public String getString() {
        return typeName + ',' + "\"" + taxCoefficient + "\"";
    }

    @Override
    public String toString() {
        return typeName +
                ", taxCoefficient=" + taxCoefficient;
    }
}
