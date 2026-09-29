package util.collections;

import java.util.*;

public class Bag<T> implements Iterable<T>
{
    private final Map<T, Integer> data;

    public Bag() {
        this.data = new HashMap<>();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        List<T> elements = new ArrayList<>();
        copyToList(elements);
        return elements.iterator();
    }

    public void add(T elem) {
        this.data.put(elem, this.data.getOrDefault(elem, 0) + 1);
    }

    public void remove(T elem) throws IllegalAccessException {
        if (this.data.get(elem) == null)
        {
            throw new IllegalAccessException();
        }

        if (this.data.get(elem) == 1) {
            this.data.remove(elem);
        } else {
            this.data.put(elem, this.data.get(elem) - 1);
        }
    }

    public int count(T elem) {
        return this.data.getOrDefault(elem, 0);
    }

    public void copyToList(List<? super T> destination) {
        for (Map.Entry<T, Integer> entry : data.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                destination.add(entry.getKey());
            }
        }
    }

    public void drainToList(List<? super T> destination) {
        Iterator<Map.Entry<T, Integer>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<T, Integer> entry = it.next();
            for (int i = 0; i < entry.getValue(); ++i) {
                destination.add(entry.getKey());
            }
            it.remove();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, Integer> entry : data.entrySet()) {
            for (int i = 0; i < entry.getValue(); ++i) {
                sb.append(entry.getKey());
                sb.append('\n');
            }
        }

        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
}
