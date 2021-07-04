package dercochenko.com.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleType {
    private int id;
    private String typeName;
    private double taxCoefficient;
    private static int counter = 0;
    public static ArrayList<VehicleType> vehicleTypes = new ArrayList<>();

    public VehicleType(String typeName, double taxCoefficient) {
        this.id = counter++;
        this.typeName = typeName;
        this.taxCoefficient = taxCoefficient;
    }

    public static void addVehicleTypeInList(String typeName, double taxCoefficient) {
        if (taxCoefficient >= 0 && typeName != null) {
            vehicleTypes.add(new VehicleType(typeName, taxCoefficient));
        }
    }

    public static void addVehicleTypeInList(List<VehicleType> vehicleTypes) {
        if (vehicleTypes != null) {
            VehicleType.vehicleTypes = (ArrayList<VehicleType>) vehicleTypes;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return typeName +
                "  " + taxCoefficient;
    }
}
