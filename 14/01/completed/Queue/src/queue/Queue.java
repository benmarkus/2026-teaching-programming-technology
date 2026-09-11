package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private List<String> data;

    public Queue () {
        this.data = new ArrayList<>();
    }

    public void push(String elem) {
        data.addLast(elem);
    }

    public String pop() {
        return data.removeFirst();
    }

    public String top() {
        return data.getFirst();
    }

    public int size() {
        return data.size();
    }
}