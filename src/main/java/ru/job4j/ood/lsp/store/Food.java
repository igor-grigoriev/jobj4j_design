package ru.job4j.ood.lsp.store;

import java.time.LocalDate;

public abstract class Food {
    private final String name;
    private double price;
    private double discount;
    private final LocalDate createDate;
    private final LocalDate expiryDate;

    public Food(String name, double price, double discount, LocalDate createDate, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}