package deque;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    protected int size;
    private int nextFirst;
    private int nextLast;
    private final int InitLength = 8;

    public ArrayDeque() {
        items = (T[]) new Object[InitLength];
        size = 0;
        nextFirst = InitLength - 1;
        nextLast = 0;
    }

    public void resize(int length) {
        T[] resized = (T[]) new Object[length];
        if (plusOne(nextFirst) > minusOne(nextLast)) {
            int back = items.length - plusOne(nextFirst);
            int front = size - back;
            System.arraycopy(items, nextFirst + 1, resized, 0, back);
            System.arraycopy(items, 0, resized, back, front);
        } else {
            System.arraycopy(items, nextFirst + 1, resized, 0, size);
        }
        items = resized;
        nextFirst = length - 1;
        nextLast = size;
    }

    private void checkExpand() {
        if (size == items.length) {
            resize(size * 2);
        }
    }

    private void checkReduce() {
        if (size < items.length / 4 && items.length > 8) {
            resize(items.length / 4);
        }
    }

    private int plusOne(int x) {
        return (x + 1) % items.length;
    }

    private int minusOne(int x) {
        return (x - 1) % items.length;
    }

    @Override
    public void addFirst(T item) {
        checkExpand();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        checkExpand();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(get(i));
            ;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        checkReduce();
        nextFirst = plusOne(nextFirst);
        size -= 1;
        T item = items[nextFirst];
        items[nextFirst] = null;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        checkReduce();
        nextLast = minusOne(nextLast);
        size -= 1;
        T item = items[nextLast];
        items[nextLast] = null;
        return item;
    }

    @Override
    public T get(int index) {
        return items[(index + nextFirst + 1) % items.length];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator it = new Iterator() {
            int currIndex = 0;

            @Override
            public boolean hasNext() {
                return plusOne(currIndex) != nextLast;
            }

            @Override
            public Object next() {
                T item = get(currIndex);
                currIndex += 1;
                return item;
            }
        };
        return it;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ArrayDeque oas) {
            if (oas.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (get(i) != oas.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
