package org.persistence;

import org.vehicles.Vehicle;
import org.vehicles.VehicleFactory;
import org.vehicles.fuel.GasPumps;
import org.vehicles.parts.InvalidLicensePlateFormatException;
import org.vehicles.parts.LicensePlate;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

public class FileVehicleRepository implements VehicleRepository
{
    private static final Path filepath =
        Path.of(System.getProperty("data.dir", "data"), "vehicles.txt");

    private List<Vehicle> cache;

    @Override public List<Vehicle> getAllVehicles() {
        return getAllVehiclesThat(vehicle -> true);
    }

    @Override public List<Vehicle> getAllVehiclesThat(Predicate<Vehicle> condition) {
        if (cache == null) {
            cache = readAllVehicles();
        }

        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : cache) {
            if (condition.test(vehicle)) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    private List<Vehicle> readAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(filepath);
             Scanner scanner = new Scanner(br))
        {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }

                vehicles.add(parseVehicleRecord(line));
            }
        } catch (IOException e) {
            throw new VehicleDataException("Could not read data source file '" + filepath + "'", e);
        }

        return vehicles;
    }

    private Vehicle parseVehicleRecord(String recordLine) {
        try (Scanner lineScanner = new Scanner(recordLine)) {
            String typeCode = lineScanner.next();
            String plateNumber = lineScanner.next();
            int pumpCount = lineScanner.nextInt();

            List<GasPumps> gasPumps = new ArrayList<>();
            for (int i = 0; i < pumpCount; ++i) {
                gasPumps.add(new GasPumps(lineScanner.nextInt()));
            }

            LicensePlate licensePlate = LicensePlate.create(plateNumber);

            return VehicleFactory.create(typeCode, licensePlate, gasPumps);
        } catch (NoSuchElementException | IllegalArgumentException | InvalidLicensePlateFormatException e) {
            throw new VehicleDataException("Malformed vehicle record: '" + recordLine + "'", e);
        }
    }
}
