package com.virudhairaj.feedcore;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * this class holds list of objects that passed from adapter to viewholder
 */
public class Args implements Collection<Object> {
    final private List<Object> objects;

    public Args() {
        objects = new ArrayList<>();
    }

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public boolean isEmpty() {
        return objects.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return objects.contains(o);
    }

    @NonNull
    @Override
    public Iterator<Object> iterator() {
        return objects.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return objects.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return objects.toArray(a);
    }

    @Override
    public boolean add(Object o) {
        return objects.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return objects.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return objects.containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull Collection<?> c) {
        return objects.addAll(c);
    }

    public boolean addAll(Object... c) {
        return c != null && objects.addAll(Arrays.asList(c));
    }


    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return objects.removeAll(c);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return objects.retainAll(c);
    }

    @Override
    public void clear() {
        objects.clear();
    }
}
