package dercochenko.com.Vehicle.Engine;

public abstract class CombustionEngine extends AbstractEngine {
    private double fuelTankCapacity;
    private double fuelConsumptionPer100;

    public CombustionEngine(String typeEngine, double roadTaxRate, double fuelTankCapacity, double fuelConsumptionPer100) {
        super(typeEngine, roadTaxRate);
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelConsumptionPer100 = fuelConsumptionPer100;
    }

    @Override
    public String toString() {
        return super.toString()+"  " + fuelTankCapacity +
                "  " + fuelConsumptionPer100;
    }

    @Override
    public double getTaxPerMonth() {
        return super.getTaxPerMonth();
    }

    @Override
    public double getMaxKilometers() {
        return Math.ceil((fuelTankCapacity * 100 / fuelConsumptionPer100) * 10) / 10;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public double getFuelConsumptionPer100() {
        return fuelConsumptionPer100;
    }
}
