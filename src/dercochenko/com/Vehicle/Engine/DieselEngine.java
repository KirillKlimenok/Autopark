package dercochenko.com.Vehicle.Engine;

public class DieselEngine extends CombustionEngine{
    public DieselEngine(double fuelTankCapacity, double fuelConsumptionPer100) {
        super("Diesel", 1.2, fuelTankCapacity, fuelConsumptionPer100);
    }
}
