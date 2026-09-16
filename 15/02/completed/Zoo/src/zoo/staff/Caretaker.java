package zoo.staff;

import zoo.animals.Animal;

public class Caretaker {

    public void takeCareOf(Iterable<Animal> animals) {
        animals.forEach((this::takeCareOf));
    }
    public void takeCareOf(Animal animal) {
        System.out.println("Caretaker took care of " + animal.getClass().getSimpleName());
    }
}
