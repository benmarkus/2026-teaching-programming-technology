package org.vehicles;

import org.vehicles.fuel.GasPumps;
import org.vehicles.parts.LicensePlate;

import java.util.List;

public final class Car extends Vehicle
{
    public Car(LicensePlate licensePlate, List<GasPumps> gasPumps) {
        super(licensePlate, gasPumps);
    }

    @Override
    protected int getMaxGasPumpCapacityInLitres() {
        return 60;
    }
}
