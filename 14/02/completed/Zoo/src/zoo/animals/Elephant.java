package zoo.animals;

public non-sealed class Elephant extends WildAnimal
{
    @Override public void move() {
        System.out.println("Elephant moves.");
    }

    @Override public void makeSound() {
        System.out.println("TRÖÖÖÖÖT");
    }
}
