import util.collections.Bag;
import util.collections.BagAlgorithms;
import util.collections.FailFastBag;

import java.util.*;

public class Main {

    public static long numberOfSharedElements(Collection<?> col1, Collection<?> col2) {
        Set<?> set1 = new HashSet<>(col1);
        return col2.stream().distinct().filter(set1::contains).count();
    }

    private static void demoBasicBag() {
        Bag<String> bag = new Bag<>();
        bag.add("Turbo");
        bag.add("Turbo");
        bag.add("Tibi");

        List<Object> objects = new ArrayList<>();
        bag.copyToList(objects);
        System.out.println(objects);
        System.out.println(bag);

        List<Object> bagElements = new ArrayList<>();
        bag.copyToList(bagElements);
        System.out.println(numberOfSharedElements(objects, bagElements));

        List<Object> objects2 = new ArrayList<>();
        bag.drainToList(objects2);
        System.out.println(objects2);
        System.out.println(bag);
    }

    private static void demoAlgorithms() {
        Bag<String> a = new Bag<>();
        a.add("apple");
        a.add("apple");
        a.add("banana");

        Bag<String> b = new Bag<>();
        b.add("apple");
        b.add("cherry");
        b.add("cherry");

        System.out.println("union:");
        System.out.println(BagAlgorithms.union(a, b));

        System.out.println("intersection:");
        System.out.println(BagAlgorithms.intersection(a, b));

        System.out.println("difference (a - b):");
        System.out.println(BagAlgorithms.difference(a, b));

        System.out.println("most common in a: " + BagAlgorithms.mostCommon(a));

        System.out.println("histogram of a:");
        BagAlgorithms.printHistogram(a);
    }

    private static void demoFailFastBag() {
        FailFastBag<String> bag = new FailFastBag<>();
        bag.add("Turbo");
        bag.add("Tibi");
        bag.add("Morzsi");

        Iterator<String> it = bag.iterator();
        System.out.println(it.next());

        bag.add("Buksi");

        try {
            System.out.println(it.next());
            System.out.println("Did not throw -- unexpected.");
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException, as expected.");
        }

        // A FailFastBag is a Bag, so it works with BagAlgorithms too:
        System.out.println("most common in bag: " + BagAlgorithms.mostCommon(bag));
    }

    public static void main(String[] args) {
        demoBasicBag();
        System.out.println();
        demoAlgorithms();
        System.out.println();
        demoFailFastBag();
    }
}
