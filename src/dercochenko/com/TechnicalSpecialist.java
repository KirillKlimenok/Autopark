package dercochenko.com;

import dercochenko.com.Vehicle.Engine.CombustionEngine;
import dercochenko.com.Vehicle.Engine.ElectricalEngine;
import dercochenko.com.Vehicle.Engine.Startable;
import dercochenko.com.Vehicle.Vehicle;
import dercochenko.com.Vehicle.VehicleType;

public class TechnicalSpecialist {
    private static final int LOWER_LIMIT_MANUFACTURE_YEAR = 1886;

    public static boolean validateManufactureYear(int year) {
        return LOWER_LIMIT_MANUFACTURE_YEAR <= year;
    }

    public static boolean validateMileage(double mileage) {
        return mileage >= 0;
    }

    public static boolean validateWeight(double weight) {
        return weight > 0;
    }

    public static boolean validateColorString(String color) {
        try {
            Vehicle.Color.valueOf(color);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    static public boolean validateVehicleType(String type) throws IllegalArgumentException {
        for (int i = 0; i < VehicleType.vehicleTypes.size(); i++) {
            VehicleType v = VehicleType.vehicleTypes.get(i);
            if (v.getTypeName().equals(type) && v.getTaxCoefficient() >= 0) return true;
        }
        return false;
    }

    static public boolean validateRegistrationNumber(String number) {
        if (number == null) return false;

        return number.matches("(\\d{4}\\s[A-Z]{2}[-]\\d)");
    }

    static public boolean validateModelName(String str) {
        return !str.isEmpty();
    }

    static public boolean validateEngine(Startable startable) {
        if (startable instanceof ElectricalEngine engine) {
            return engine.getElectricityConsumption() > 0 && engine.getBatterySize() > 0;
        } else {
            if (startable instanceof CombustionEngine engine) {
                return engine.getFuelTankCapacity() > 0 && engine.getFuelConsumptionPer100() > 0 && engine.getTaxPerMonth() > 0;
            } else {
                return false;
            }
        }
    }
}
