package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T item = get(0);
        for (int i = 0; i < size; i++) {
            if (cmp.compare(get(i), item) > 0) {
                item = get(i);
            }
        }
        return item;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T item = get(0);
        for (int i = 0; i < size; i++) {
            if (c.compare(get(0), item) > 0) {
                item = get(i);
            }
        }
        return item;
    }
}
