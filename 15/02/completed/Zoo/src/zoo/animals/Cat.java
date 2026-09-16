package zoo.animals;

public non-sealed class Cat extends Pet {
    @Override public void move() {
        System.out.println("Cat moves.");
    }

    @Override public void makeSound() {
        System.out.println("Meow");
    }
}
