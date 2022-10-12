package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node() {
            item = null;
            next = prev = null;
        }

        public Node(Node p, T item, Node n) {
            prev = p;
            this.item = item;
            next = n;
        }
    }

    private int size;
    private final Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node node = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node curr = sentinel.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private Node curr = sentinel.next;

            @Override
            public boolean hasNext() {
                return curr != sentinel;
            }

            @Override
            public T next() {
                T item = curr.item;
                curr = curr.next;
                return item;
            }
        };
        return it;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque a = (Deque) o;
        if (a.size()!=size){
            return false;
        }
        Node curr = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (a.get(i) != sentinel.item) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }
}
