package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class Warehouse extends AbstractStore {
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
        LocalDate createDate = food.getCreateDate();
        LocalDate expiryDate = food.getExpiryDate();
        long expiry = DAYS.between(createDate, expiryDate);
        long remain = DAYS.between(LocalDate.now(), expiryDate);
        return (double) remain / expiry > 0.75;
    }
}