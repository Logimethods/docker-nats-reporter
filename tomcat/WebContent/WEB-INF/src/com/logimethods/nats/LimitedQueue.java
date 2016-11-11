package com.logimethods.nats;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {
    private int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > limit) { super.remove(); }
        return true;
    }

    @Override
    public void add(int index, E o) {
        super.add(index, o);
        while (size() > limit) { super.removeLast(); }
    }
}