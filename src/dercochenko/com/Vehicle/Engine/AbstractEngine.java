package dercochenko.com.Vehicle.Engine;

import java.util.Objects;

public abstract class AbstractEngine implements Startable {
    private String typeEngine;
    private double roadTaxRate;

    public AbstractEngine(String typeEngine, double roadTaxRate) {
        this.typeEngine = typeEngine;
        this.roadTaxRate = roadTaxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEngine that = (AbstractEngine) o;
        return Double.compare(that.roadTaxRate, roadTaxRate) == 0 && Objects.equals(typeEngine, that.typeEngine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeEngine, roadTaxRate);
    }

    @Override
    public double getTaxPerMonth() {
        return roadTaxRate;
    }

    @Override
    public double getMaxKilometers() {
        return 0;
    }

    @Override
    public String toString() {
        return typeEngine +
                "  " + roadTaxRate;
    }

    public String getTypeEngine() {
        return typeEngine;
    }

    public void setTypeEngine(String typeEngine) {
        this.typeEngine = typeEngine;
    }

    public double getRoadTaxRate() {
        return roadTaxRate;
    }

    public void setRoadTaxRate(double roadTaxRate) {
        this.roadTaxRate = roadTaxRate;
    }
}
