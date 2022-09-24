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
            expand();
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
        capacity = table.length * 2;
        count = 0;
        modCount = 0;
        MapEntry<K, V>[] rsl = new MapEntry[capacity];
        for (MapEntry<K, V> enrty : table) {
            if (enrty != null) {
                int index = (enrty.key != null) ? indexFor(hash(enrty.key.hashCode())) : indexFor(0);
                if (rsl[index] == null) {
                    rsl[index] = new MapEntry<>(enrty.key, enrty.value);
                    count++;
                    modCount++;
                }
            }
        }
        table = rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = (key != null) ? indexFor(hash(key.hashCode())) : indexFor(0);
        if (table[index] != null) {
            K k = table[index].key;
            int hashCodeK = (k != null) ? k.hashCode() : 0;
            int hashCodeKey = (key != null) ? key.hashCode() : 0;
            if (hash(hashCodeK) == hash(hashCodeKey) && (k == key || Objects.equals(k, key))) {
                rsl = table[index].value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = (key != null) ? indexFor(hash(key.hashCode())) : indexFor(0);
        if (table[index] != null) {
            K k = table[index].key;
            int hashCodeK = (k != null) ? k.hashCode() : 0;
            int hashCodeKey = (key != null) ? key.hashCode() : 0;
            if (hash(hashCodeK) == hash(hashCodeKey) && (k == key || Objects.equals(k, key))) {
                table[index] = null;
                rsl = true;
                count--;
                modCount++;
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
}