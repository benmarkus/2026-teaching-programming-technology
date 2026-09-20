package zoo;

import zoo.animals.*;
import zoo.staff.Caretaker;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Animal> animals = List.of(
                new Cat(),
                new Dog(),
                new Elephant()
        );

        Zoo zoo = new Zoo(animals);
        zoo.step();
        Caretaker caretaker = new Caretaker();
        caretaker.takeCareOf(zoo.animals.getFirst());
        caretaker.takeCareOf(zoo.animals);

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        Animal animal;
        if (choice > 0) {
            animal = new Hamster();
        } else {
            animal = new Elephant();
        }
        switch (animal) {
            case Hamster h -> h.move();
            case Elephant e -> e.makeSound();
            case Cat cat -> { }
            case Dog dog -> { }
            case Lion lion -> { }
        }
    }
}