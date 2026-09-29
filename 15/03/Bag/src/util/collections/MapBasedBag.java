package util.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapBasedBag<T>
{
    private Map<T, Integer> data;

    public MapBasedBag() {
        this.data = new HashMap<>();
    }

    public void add(T elem) {
        data.put(elem, data.getOrDefault(elem, 0) + 1);
    }

    public void remove(T elem) throws IllegalAccessException {
        if (!data.containsKey(elem)) {
            throw new IllegalAccessException();
        }

        if (data.get(elem) == 1) {
            data.remove(elem);
        } else {
            data.put(elem, data.get(elem) - 1);
        }
    }

    public void copyToList(List<? super T> dest) {
        for (var entry : data.entrySet()) {
            for (int i = 0; i < entry.getValue(); ++i) {
                dest.add(entry.getKey());
            }
        }
    }

    public void drainToList(List<? super T> dest) {
        
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
