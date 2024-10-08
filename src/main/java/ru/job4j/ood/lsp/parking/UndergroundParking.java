package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class UndergroundParking implements Parking {
    private final List<Cell> truckCells = new ArrayList<>();
    private final List<Cell> passengerCells = new ArrayList<>();

    public UndergroundParking(int truckCellCount, int passengerCellCount) {
        for (int i = 0; i < truckCellCount; i++) {
            truckCells.add(new TruckCell());
        }
        for (int i = 0; i < passengerCellCount; i++) {
            passengerCells.add(new PassengerCell());
        }
    }

    public boolean park(Car car) {
        boolean result = false;
        if (car.getSize() > 1) {
            for (Cell cell : truckCells) {
                result = cell.input(car);
                if (result) {
                    return result;
                }
            }
            long unbusyPassengerCellsCount = getUnbusyPassengerCellsCount();
            if (car.getSize() <= unbusyPassengerCellsCount) {
                int count = 0;
                for (Cell cell : passengerCells) {
                    if (count == car.getSize()) {
                        return true;
                    }
                    if (cell.input(car)) {
                        count++;
                    }
                }
            }
        } else {
            for (Cell cell : passengerCells) {
                result = cell.input(car);
                if (result) {
                    return result;
                }
            }
        }
        return result;
    }

    public void unpark(Car car) {
        boolean unpark = false;
        if (car.getSize() > 1) {
            for (Cell cell : truckCells) {
                unpark = cell.output(car);
                if (unpark) {
                    break;
                }
            }
            if (!unpark) {
                for (Cell cell : passengerCells) {
                    cell.output(car);
                }
            }
        } else {
            for (Cell cell : passengerCells) {
                unpark = cell.output(car);
                if (unpark) {
                    break;
                }
            }
        }
    }

    public long getBusyCellsCount() {
        return getBusyTruckCellsCount() + getBusyPassengerCellsCount();
    }

    public long getUnbusyCellsCount() {
        return getUnbusyTruckCellsCount() + getUnbusyPassengerCellsCount();
    }

    public long getBusyTruckCellsCount() {
        return truckCells.stream().filter(Cell::isBusy).count();
    }

    public long getUnbusyTruckCellsCount() {
        return truckCells.stream().filter(cell -> !cell.isBusy()).count();
    }

    public long getBusyPassengerCellsCount() {
        return passengerCells.stream().filter(Cell::isBusy).count();
    }

    public long getUnbusyPassengerCellsCount() {
        return passengerCells.stream().filter(cell -> !cell.isBusy()).count();
    }
}