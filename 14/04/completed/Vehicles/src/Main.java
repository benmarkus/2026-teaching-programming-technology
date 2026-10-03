import org.persistence.FileVehicleRepository;
import org.persistence.VehicleRepository;
import org.vehicles.Vehicle;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehicleRepository repository = new FileVehicleRepository();

        List<Vehicle> oversizedGasPump = repository.getAllVehiclesThat(Vehicle::hasOversizedGasPump);
        List<Vehicle> overCapacity = repository.getAllVehiclesThat(Vehicle::hasTotalCapacityOverOneThousandLitres);

        System.out.println("Tul nagy kutnal tankolo jarmuvek:");
        oversizedGasPump.forEach(vehicle -> System.out.println("  " + vehicle));

        System.out.println();
        System.out.println("1000 liternel nagyobb ossz-kapacitasu jarmuvek:");
        overCapacity.forEach(vehicle -> System.out.println("  " + vehicle));
    }
}
