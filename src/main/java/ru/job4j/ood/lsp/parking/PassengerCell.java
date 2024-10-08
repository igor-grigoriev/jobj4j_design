package ru.job4j.ood.lsp.parking;

public class PassengerCell implements Cell {
    @Override
    public boolean isBusy() {
        return false;
    }

    @Override
    public boolean input(Car car) {
        return false;
    }

    @Override
    public boolean output(Car car) {
        return false;
    }
}