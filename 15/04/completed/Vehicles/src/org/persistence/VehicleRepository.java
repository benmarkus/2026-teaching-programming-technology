package org.persistence;

import org.vehicles.Vehicle;

import java.util.List;
import java.util.function.Predicate;

public interface VehicleRepository
{
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesThat(Predicate<Vehicle> condition);
}
