package zoo.animals;

public sealed abstract class Pet extends Animal
        permits Cat, Dog, Hamster
{

}
