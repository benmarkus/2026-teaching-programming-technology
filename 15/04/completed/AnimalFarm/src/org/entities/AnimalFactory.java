package org.entities;

import org.meal.Meal;

public final class AnimalFactory
{
    private AnimalFactory() { }

    public static Animal create(String speciesCode, String name, int weight, Meal[] meals) {
        return switch (speciesCode) {
            case "T" -> new Cow(name, weight, meals);
            case "L" -> new Horse(name, weight, meals);
            case "K" -> new Goat(name, weight, meals);
            case "E" -> new Emu(name, weight, meals);
            default -> throw new IllegalArgumentException("Unknown animal species code: '" + speciesCode + "'");
        };
    }
}
