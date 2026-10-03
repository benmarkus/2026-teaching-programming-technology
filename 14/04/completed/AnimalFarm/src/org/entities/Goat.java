package org.entities;

import org.meal.Meal;

public final class Goat extends Animal
{
    public Goat(String name, int weight, Meal[] meals) {
        super(name, weight, meals);
    }

    @Override
    protected int getHealthyWeightThresholdInKg() {
        return 12;
    }
}
