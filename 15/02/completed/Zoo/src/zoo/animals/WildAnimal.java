package zoo.animals;

public sealed abstract class WildAnimal extends Animal
        permits Lion, Elephant
{

}
