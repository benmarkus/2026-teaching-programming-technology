package queue;

import java.util.Scanner;

public class TestQueue {

    public static void main(String[] args) {
        // TODO: implement a Queue class (push, pop, size) and test it here
        Queue queue = new Queue();
        queue.push("Sziamiaú");
        queue.push("Hókuszpók");

        System.out.println(queue.top().equals("Sziamiaú"));
        System.out.println(queue.pop());
    }
}
