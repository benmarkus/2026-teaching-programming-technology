/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

public class TestQueue {

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.push("Sziamiaú");
        queue.push("Hókuszpók");

        /*
        Nagyon vigyázzunk azzal, hogy objektumokat hogyan hasonlítunk össze!
        Bár az alábbi kódrészlet igazat fog dobni, és ezt is várnánk, ez
        hosszabb stringeknél nem feltétlenül igaz.

        A == operátor referencia szerint hasonlít össze, azaz azt nézi meg, hogy két
        objektum referenciája egyezik-e.
        Az alábbi esetben a Small String Optimization (nem csak Javaban van ilyen,
        hanem kb bármelyik prognyelven) miatt nem lesz probléma.
         */
        System.out.println(queue.top() == "Sziamiaú"); // rossz módszer
        System.out.println(queue.top().equals("Sziamiaú")); // helyes módszer

        queue.pop(); // a visszatérési értéket nem feltétlenül kell felhasználni
        queue.empty();
        System.out.println(queue.isEmpty());
    }
}
