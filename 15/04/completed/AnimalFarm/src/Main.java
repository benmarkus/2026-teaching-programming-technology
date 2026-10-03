import org.entities.Animal;
import org.persistence.AnimalRepository;
import org.persistence.FileAnimalRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnimalRepository repository = new FileAnimalRepository();

        List<Animal> pathologicallySkinny = repository.getAllAnimalsThat(Animal::isPathologicallySkinny);
        List<Animal> ateTooMuchToday = repository.getAllAnimalsThat(Animal::ateMoreThanOneKilogramToday);

        System.out.println("Korosan sovany allatok:");
        pathologicallySkinny.forEach(animal -> System.out.println("  " + animal));

        System.out.println();
        System.out.println("Tobb mint 1 kg elelmet fogyasztott allatok:");
        ateTooMuchToday.forEach(animal -> System.out.println("  " + animal));
    }
}
