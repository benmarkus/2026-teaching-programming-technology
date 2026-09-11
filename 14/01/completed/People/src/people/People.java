package people;

public class People {

    public static void main(String[] args) {
        // TODO: create a Person, change their age, then print the new age
        Person person = new Person("Tibor", 80, "HU");
        person.setAge(81);
        System.out.println(person);

        int number = 4;
        number = increase(number);

        System.out.println(number);

        Integer a = 127;
        Integer b = 127;

        Integer c = 128;
        Integer d = 128;

        System.out.println(a == b);
        System.out.println(c == d);

    }

    public static int increase(int number) {
        number += 3;
        return number;
    }

}
