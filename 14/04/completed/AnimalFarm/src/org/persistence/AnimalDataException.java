package org.persistence;

public class AnimalDataException extends RuntimeException
{
    public AnimalDataException(String message) {
        super(message);
    }

    public AnimalDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
