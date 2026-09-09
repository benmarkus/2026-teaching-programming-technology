/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

public class People {

    public static void main(String[] args) {
        Person p1 = new Person("Doug", 31, "US");
        System.out.println("p1 is " + p1.getName());
        Person p2 = new Person("Anne", 42, "NED");
        System.out.println("p2 is " + p2.getName());        
        changeName(p2);
        System.out.println("p2's name changed: " + p2.getName());

        /*
        Java-ban primitív típusokat érték szerint adunk át,
        az alábbi kódrészletben a changeInt metódusnak átadott i
        paraméterről másolat készül, ezért a kint hivatkozott i
        értéke nem változik meg a metódushívás alatt.
         */
        int i = 5;
        changeInt(i);
        System.out.println("i's value has not changed: " + i);

        /*
        Így oldható meg a változtatás.
         */
        i = increaseInt(i);
        System.out.println("we can change i's value by returning the new value: " + i);
    }
    
    public static void changeName(Person p) {
        p.setName("Frank");
    }
    
    public static void changeInt(int param) {
        param += 6;
    }
    
    public static int increaseInt(int param) {
        return param + 6;
    }

}
