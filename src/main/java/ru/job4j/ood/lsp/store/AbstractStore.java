package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected final List<Food> foods = new ArrayList<>();

    public boolean add(Food food) {
        return foods.add(food);
    }

    public boolean delete(Food food) {
        int index = foods.indexOf(food);
        boolean rsl = index != -1;
        if (rsl) {
            foods.remove(index);
        }
        return rsl;
    }
}