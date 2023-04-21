package ru.job4j.ood.lsp.store;

import java.time.LocalDate;

public class Milk extends Food {
    public Milk(String name, double price, double discount, LocalDate createDate, LocalDate expiryDate) {
        super(name, price, discount, createDate, expiryDate);
    }
}