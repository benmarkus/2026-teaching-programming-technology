package org.entities;

import org.meal.Meal;

public final class Emu extends Animal
{
    public Emu(String name, int weight, Meal[] meals) {
        super(name, weight, meals);
    }

    @Override
    protected int getHealthyWeightThresholdInKg() {
        return 20;
    }
}
