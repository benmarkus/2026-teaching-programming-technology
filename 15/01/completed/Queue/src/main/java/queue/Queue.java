/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import java.util.ArrayList;
import java.util.List;

/*
A queue tulajdonképpen felfogható egy lista megszorításokkal ellátott
változataként. Azzal, ha a magábanfoglalt lista adattagot elrejtjük
azzal hogy priváttá tesszük, majd saját publikus metódusokat adunk meg,
kontrollálni tudjuk, hogy hogyan férhet hozzá a külvilág az adatainkhoz.

Egy queue jellemzően kizárólag query metódusokat (belső állapotot nem módosító,
csak lekérő), továbbá a push (lista hátuljára tolás) és pop (lista elejéről kivétel),
esetleg törlés műveleteket ismeri.
 */
public class Queue {
    // nem működik, generikus paraméter nem lehet primitív típus
    // private final List<int> data;

    /* szokás szerint az interfészt szoktuk megadni fieldeknél ahol lehet
       lásd: OEP - Dependency Inversion Principle
     */
    private final List<String> data;

    public Queue() {
        /*
        itt már a konkrét List implementációnak kell szerepelnie
         */
        this.data = new ArrayList<>();
    }
    
    public void push(String e) {
        data.add(e);
    }
    
    public String top() {
        if (data.isEmpty()) {
            return null;
        } else {
            return data.getFirst();
        }
    }

    public String pop() {
        if (data.isEmpty()) {
            return null;
        } else {
            return data.removeFirst();
        }
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void empty() {
        data.clear();
    }

    /*
    A System.out.println metódus egy Stringet vár, és amikor
    egy objektumot átadunk neki, akkor automatikusan az objektum toString
    metódusát fogja felhasználni, ezt felüldefiniálva tudunk saját szöveges
    megjelenítést megadni.
     */
    @Override
    public String toString() {
        return "Queue{" + "data=" + data + '}';
    }    
}