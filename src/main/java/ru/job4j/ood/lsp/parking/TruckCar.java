package ru.job4j.ood.lsp.parking;

public class TruckCar implements Car {
    private final int size;

    public TruckCar() {
        this.size = 2;
    }

    public TruckCar(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Размер грузового автомобиля не может быть меншье 2");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}