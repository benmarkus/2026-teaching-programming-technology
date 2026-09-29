package util.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class FailFastBag<T> extends Bag<T> {

    private int modCount = 0;

    @Override
    public void add(T elem) {
        super.add(elem);
        modCount++;
    }

    @Override
    public void remove(T elem) throws IllegalAccessException {
        super.remove(elem);
        modCount++;
    }

    @Override
    public void drainToList(List<? super T> destination) {
        super.drainToList(destination);
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> snapshot = super.iterator();
        int expectedModCount = modCount;

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return snapshot.hasNext();
            }

            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return snapshot.next();
            }
        };
    }
}
