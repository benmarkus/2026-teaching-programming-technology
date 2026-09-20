package zoo.animals;

public non-sealed class Dog extends Pet
{
    @Override public void move() {
        System.out.println("Dog moves.");
    }

    @Override public void makeSound() {
        System.out.println("Woof");
    }
}
