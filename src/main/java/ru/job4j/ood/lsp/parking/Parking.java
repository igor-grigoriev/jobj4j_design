package ru.job4j.ood.lsp.parking;

public interface Parking {
    boolean park(Car car);
    void unpark(Car car);
    long getBusyCellsCount();
    long getUnbusyCellsCount();
}