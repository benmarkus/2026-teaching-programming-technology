package org.vehicles;

import org.vehicles.fuel.GasPumps;
import org.vehicles.parts.LicensePlate;

import java.util.List;

public final class VehicleFactory
{
    private VehicleFactory() { }

    public static Vehicle create(String typeCode, LicensePlate licensePlate, List<GasPumps> gasPumps) {
        return switch (typeCode) {
            case "C" -> new Car(licensePlate, gasPumps);
            case "B" -> new Bus(licensePlate, gasPumps);
            case "T" -> new Truck(licensePlate, gasPumps);
            default -> throw new IllegalArgumentException("Unknown vehicle type code: '" + typeCode + "'");
        };
    }
}
