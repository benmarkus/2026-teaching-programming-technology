package org.entities;

import org.meal.Meal;

public final class Cow extends Animal
{
    public Cow(String name, int weight, Meal[] meals) {
        super(name, weight, meals);
    }

    @Override
    protected int getHealthyWeightThresholdInKg() {
        return 100;
    }
}
