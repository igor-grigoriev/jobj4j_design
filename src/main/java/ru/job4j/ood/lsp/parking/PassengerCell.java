package ru.job4j.ood.lsp.parking;

public class PassengerCell implements Cell {
    private Car car;

    public boolean input(Car car) {
        if (!isBusy() && car != null) {
            this.car = car;
            return true;
        }
        return false;
    }

    public boolean output(Car car) {
        if (isBusy() && this.car.equals(car)) {
            this.car = null;
            return true;
        }
        return false;
    }

    public boolean isBusy() {
        return car != null;
    }
}