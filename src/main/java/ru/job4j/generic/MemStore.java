package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return model.equals(storage.computeIfPresent(id, (s, t) -> model));
    }

    @Override
    public boolean delete(String id) {
        return storage.containsKey(id) && storage.get(id).equals(storage.remove(id));
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}