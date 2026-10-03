package org.persistence;

public class VehicleDataException extends RuntimeException
{
    public VehicleDataException(String message) {
        super(message);
    }

    public VehicleDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
