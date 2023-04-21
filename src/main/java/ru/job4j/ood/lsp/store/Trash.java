package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class Trash extends AbstractStore {
    @Override
    public boolean add(Food food) {
        if (isValid(food)) {
            return foods.add(food);
        }
        return false;
    }

    @Override
    public boolean delete(Food food) {
        int index = foods.indexOf(food);
        boolean rsl = index != -1;
        if (rsl && !isValid(food)) {
            foods.remove(index);
        }
        return rsl;
    }

    private boolean isValid(Food food) {
        return DAYS.between(LocalDate.now(), food.getExpiryDate()) < 0;
    }
}