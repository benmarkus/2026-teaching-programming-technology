package org.vehicles;

import org.vehicles.fuel.GasPumps;
import org.vehicles.parts.LicensePlate;

import java.util.List;

public sealed abstract class Vehicle
    permits Car, Bus, Truck
{
    private static final int OVERSIZED_TOTAL_CAPACITY_IN_LITRES = 1000;

    private final LicensePlate licensePlate;
    private final List<GasPumps> gasPumps;

    protected Vehicle(LicensePlate licensePlate, List<GasPumps> gasPumps) {
        this.licensePlate = licensePlate;
        this.gasPumps = gasPumps;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    protected abstract int getMaxGasPumpCapacityInLitres();

    public boolean hasOversizedGasPump() {
        for (GasPumps pump : gasPumps) {
            if (pump.litres() > getMaxGasPumpCapacityInLitres()) {
                return true;
            }
        }
        return false;
    }

    public int getTotalGasPumpCapacityInLitres() {
        int total = 0;
        for (GasPumps pump : gasPumps) {
            total += pump.litres();
        }
        return total;
    }

    public boolean hasTotalCapacityOverOneThousandLitres() {
        return getTotalGasPumpCapacityInLitres() > OVERSIZED_TOTAL_CAPACITY_IN_LITRES;
    }

    @Override
    public String toString() {
        return "%s (%s)".formatted(licensePlate, getClass().getSimpleName());
    }
}
