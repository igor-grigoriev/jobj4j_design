package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class UndergroundParking implements Parking {
    private final List<Cell> truckCells = new ArrayList<>();
    private final List<Cell> passengerCells = new ArrayList<>();

    public UndergroundParking(int truckCellCount, int passengerCellCount) {
        for (int i = 0; i < truckCellCount; i++ ) {
            truckCells.add(new TruckCell());
        }
        for (int i = 0; i < passengerCellCount; i++ ) {
            passengerCells.add(new PassengerCell());
        }
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public void unpark(Car car) {

    }

    @Override
    public long getBusyCellsCount() {
        return 0;
    }

    @Override
    public long getUnbusyCellsCount() {
        return 0;
    }
}