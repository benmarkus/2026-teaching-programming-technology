package zoo.animals;

public sealed abstract class Animal
        permits Pet, WildAnimal
{
    public abstract void move();
    public abstract void makeSound();
}
