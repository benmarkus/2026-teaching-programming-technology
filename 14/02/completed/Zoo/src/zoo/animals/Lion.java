package zoo.animals;

public non-sealed class Lion extends WildAnimal
{
    @Override public void move() {
        System.out.println("Lion moves");
    }

    @Override public void makeSound() {
        System.out.println("ROAAAR");
    }
}
