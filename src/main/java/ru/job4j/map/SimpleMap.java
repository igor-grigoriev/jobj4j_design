package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count == capacity * LOAD_FACTOR) {
            table = resize();
        }
        int index = (key != null) ? indexFor(hash(key.hashCode())) : indexFor(0);
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {

    }

    @Override
    public V get(K key) {
        V rsl = null;
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                K k = entry.key;
                int hashCodeK = (k != null) ? k.hashCode() : 0;
                int hashCodeKey = (key != null) ? key.hashCode() : 0;
                if (hash(hashCodeK) == hash(hashCodeKey) && (k == key || Objects.equals(k, key))) {
                    rsl = entry.value;
                }
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                K k = table[i].key;
                int hashCodeK = (k != null) ? k.hashCode() : 0;
                int hashCodeKey = (key != null) ? key.hashCode() : 0;
                if (hash(hashCodeK) == hash(hashCodeKey) && (k == key || Objects.equals(k, key))) {
                    table[i] = null;
                    rsl = true;
                    count--;
                    modCount++;
                }
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private MapEntry<K, V>[] resize() {
        capacity = table.length * 2;
        return Arrays.copyOf(table, capacity);
    }
}