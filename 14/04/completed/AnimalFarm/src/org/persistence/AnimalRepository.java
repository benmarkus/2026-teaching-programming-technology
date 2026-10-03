package org.persistence;

import org.entities.Animal;

import java.util.List;
import java.util.function.Predicate;

public interface AnimalRepository
{
    List<Animal> getAllAnimals();
    List<Animal> getAllAnimalsThat(Predicate<Animal> condition);
}
