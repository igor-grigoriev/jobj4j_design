package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class Shop extends AbstractStore {
    @Override
    public boolean add(Food food) {
        double remain = getRemain(food);
        if (remain > 0 && remain < 0.25) {
            food.setPrice(food.getPrice() - food.getDiscount());
            food.setDiscount(0);
            return foods.add(food);
        } else if (remain > 0.25 && remain < 0.75) {
            return foods.add(food);
        }
        return false;
    }

    @Override
    public boolean delete(Food food) {
        int index = foods.indexOf(food);
        boolean rsl = index != -1;
        double remain = getRemain(food);
        if (rsl && (remain < 0 || remain > 0.75)) {
            foods.remove(index);
        }
        return rsl;
    }

    private double getRemain(Food food) {
        LocalDate createDate = food.getCreateDate();
        LocalDate expiryDate = food.getExpiryDate();
        long expiry = DAYS.between(createDate, expiryDate);
        long remain = DAYS.between(LocalDate.now(), expiryDate);
        return (double) remain / expiry;
    }
}