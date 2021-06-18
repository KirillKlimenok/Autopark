package dercochenko.com.Vehicle.Engine;

public class GasolineEngine extends CombustionEngine{
    public GasolineEngine(double fuelTankCapacity, double fuelConsumptionPer100) {
        super("Gasoline", 1.1, fuelTankCapacity, fuelConsumptionPer100);
    }
}
