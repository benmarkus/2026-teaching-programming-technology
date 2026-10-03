package org.vehicles;

import org.vehicles.fuel.GasPumps;
import org.vehicles.parts.LicensePlate;

import java.util.List;

public final class Truck extends Vehicle
{
    public Truck(LicensePlate licensePlate, List<GasPumps> gasPumps) {
        super(licensePlate, gasPumps);
    }

    @Override
    protected int getMaxGasPumpCapacityInLitres() {
        return 500;
    }
}
