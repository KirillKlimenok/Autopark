package dercochenko.com.Garage;

import dercochenko.com.Vehicle.Vehicle;

import java.util.Map;


public interface Fixer {
    Map<String, Integer> detectBreaking(Vehicle vehicle);

    void repair(Vehicle vehicle);

    boolean isBroken(Vehicle vehicle);

    /**
     * @param vehicle
     * @return value false - the machine is fully functional
     * value true - there are breakdowns
     */
    default boolean detectAndRepair(Vehicle vehicle) {
        vehicle.setVehicleDetailsBreaking(detectBreaking(vehicle));

        if (isBroken(vehicle)) {
            repair(vehicle);
            return true;
        }
        return false;
    }

}
