package zoo;

import zoo.animals.Animal;
import zoo.staff.Caretaker;
import zoo.staff.Restaurant;

import java.util.List;

public class Zoo {
    private Restaurant restaurant;
    public List<Animal> animals;

    public Zoo(List<Animal> animals) {
        this.restaurant = new Restaurant();
        this.animals = animals;
    }

    public void step() {
        for (Animal animal : this.animals) {
            animal.makeSound();
            animal.move();
        }
    }
}
