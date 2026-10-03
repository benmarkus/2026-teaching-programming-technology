package org.persistence;

import org.entities.Animal;
import org.entities.AnimalFactory;
import org.meal.Meal;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

public class FileAnimalRepository implements AnimalRepository
{
    private static final Path filepath =
        Path.of(System.getProperty("data.dir", "data"), "animals.txt");

    private List<Animal> cache;

    @Override public List<Animal> getAllAnimals() {
        return getAllAnimalsThat(animal -> true);
    }

    @Override public List<Animal> getAllAnimalsThat(Predicate<Animal> condition) {
        if (cache == null) {
            cache = readAllAnimals();
        }

        List<Animal> animals = new ArrayList<>();
        for (Animal animal : cache) {
            if (condition.test(animal)) {
                animals.add(animal);
            }
        }
        return animals;
    }

    private List<Animal> readAllAnimals() {
        List<Animal> animals = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(filepath);
             Scanner scanner = new Scanner(br))
        {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }

                animals.add(parseAnimalRecord(line));
            }
        } catch (IOException e) {
            throw new AnimalDataException("Could not read data source file '" + filepath + "'", e);
        }

        return animals;
    }

    private Animal parseAnimalRecord(String recordLine) {
        try (Scanner lineScanner = new Scanner(recordLine)) {
            String speciesCode = lineScanner.next();
            String name = lineScanner.next();
            int weight = lineScanner.nextInt();
            int mealCount = lineScanner.nextInt();

            Meal[] meals = new Meal[mealCount];
            for (int i = 0; i < mealCount; ++i) {
                meals[i] = new Meal(lineScanner.nextInt());
            }

            return AnimalFactory.create(speciesCode, name, weight, meals);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            throw new AnimalDataException("Malformed animal record: '" + recordLine + "'", e);
        }
    }
}
