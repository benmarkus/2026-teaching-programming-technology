package org.vehicles.parts;

import java.util.regex.Pattern;

public class LicensePlate
{
    private final String licensePlateNumber;

    private LicensePlate(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public static boolean isValidLicensePlateNumber(String raw) {
        Pattern pattern = Pattern.compile("([A-Z]{3}-[0-9]{3})|([A-Z]{2} [A-Z]{2}-[0-9]{2})");
        return pattern.matcher(raw).matches();
    }

    public static LicensePlate create(String rawText) throws InvalidLicensePlateFormatException {
        if (!isValidLicensePlateNumber(rawText)) {
            throw new InvalidLicensePlateFormatException("");
        } else {
            return new LicensePlate(rawText);
        }
    }

    @Override public String toString() { return licensePlateNumber; }
}
