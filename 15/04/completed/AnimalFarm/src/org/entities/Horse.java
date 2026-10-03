package org.entities;

import org.meal.Meal;

public final class Horse extends Animal
{
    public Horse(String name, int weight, Meal[] meals) {
        super(name, weight, meals);
    }

    @Override
    protected int getHealthyWeightThresholdInKg() {
        return 60;
    }
}
