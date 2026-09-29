package util.collections;

import java.util.HashSet;
import java.util.Set;

public final class BagAlgorithms {

    private BagAlgorithms() {
    }

    private static <T> Set<T> distinctElementsOf(Bag<T> bag) {
        Set<T> elements = new HashSet<>();
        for (T elem : bag) {
            elements.add(elem);
        }
        return elements;
    }

    public static <T> Bag<T> union(Bag<T> a, Bag<T> b) {
        Bag<T> result = new Bag<T>();
        Set<T> elements = distinctElementsOf(a);
        elements.addAll(distinctElementsOf(b));

        for (T elem : elements) {
            int count = Math.max(a.count(elem), b.count(elem));
            for (int i = 0; i < count; i++) {
                result.add(elem);
            }
        }
        return result;
    }

    public static <T> Bag<T> intersection(Bag<T> a, Bag<T> b) {
        Bag<T> result = new Bag<T>();
        for (T elem : distinctElementsOf(a)) {
            int count = Math.min(a.count(elem), b.count(elem));
            for (int i = 0; i < count; i++) {
                result.add(elem);
            }
        }
        return result;
    }

    public static <T> Bag<T> difference(Bag<T> a, Bag<T> b) {
        Bag<T> result = new Bag<T>();
        for (T elem : distinctElementsOf(a)) {
            int count = a.count(elem) - b.count(elem);
            for (int i = 0; i < count; i++) {
                result.add(elem);
            }
        }
        return result;
    }

    public static <T> T mostCommon(Bag<T> bag) {
        T best = null;
        int bestCount = -1;
        for (T elem : distinctElementsOf(bag)) {
            int count = bag.count(elem);
            if (count > bestCount) {
                bestCount = count;
                best = elem;
            }
        }
        return best;
    }

    public static <T> void printHistogram(Bag<T> bag) {
        for (T elem : distinctElementsOf(bag)) {
            System.out.println(elem + " " + "*".repeat(bag.count(elem)));
        }
    }
}
