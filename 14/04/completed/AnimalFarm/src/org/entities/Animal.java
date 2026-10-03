package org.entities;

import org.meal.Meal;

public sealed abstract class Animal
    permits Emu, Goat, Cow, Horse
{
    private static final int DECAGRAMS_PER_KILOGRAM = 100;

    private final String name;
    private final int weight;
    private final Meal[] mealsToday;

    public Animal(String name, int weight, Meal[] meals) {
        this.name = name;
        this.weight = weight;
        this.mealsToday = meals;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    protected abstract int getHealthyWeightThresholdInKg();

    public boolean isPathologicallySkinny() {
        return weight < getHealthyWeightThresholdInKg();
    }

    public int getTotalFoodConsumedTodayInDecagrams() {
        int total = 0;
        for (Meal meal : mealsToday) {
            total += meal.weightInDecagrams();
        }
        return total;
    }

    public boolean ateMoreThanOneKilogramToday() {
        return getTotalFoodConsumedTodayInDecagrams() > DECAGRAMS_PER_KILOGRAM;
    }

    @Override
    public String toString() {
        return "%s (%s, %d kg)".formatted(name, getClass().getSimpleName(), weight);
    }
}
