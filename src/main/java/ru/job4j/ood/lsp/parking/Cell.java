package ru.job4j.ood.lsp.parking;

public interface Cell {
    boolean isBusy();
    boolean input(Car car);
    boolean output(Car car);
}