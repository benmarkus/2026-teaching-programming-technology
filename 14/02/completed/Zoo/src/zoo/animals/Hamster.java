package zoo.animals;

public non-sealed class Hamster extends Pet
{
    @Override public void move() {
        System.out.println("Hamster moves.");
    }

    @Override public void makeSound() {
        System.out.println("hampter");
    }
}
