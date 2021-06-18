package dercochenko.com;

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
        if (str == null) {
            return false;
        } else {
            return !str.isEmpty();
        }
    }
}
