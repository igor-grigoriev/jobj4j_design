package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@Disabled
class UndergroundParkingTest {
    @Test
    public void whenGetCarSize() {
        Car passengerCar = new PassengerCar();
        Car truckCar = new TruckCar();
        assertThat(passengerCar.getSize()).isEqualTo(1);
        assertThat(truckCar.getSize()).isGreaterThan(1);
    }

    @Test
    public void whenInputAndOutputCar() {
        Car passengerCar = new PassengerCar();
        Car truckCar = new TruckCar();
        Cell passengerCell = new PassengerCell();
        Cell truckCell = new TruckCell();
        assertThat(passengerCell.isBusy()).isFalse();
        assertThat(passengerCell.input(passengerCar)).isTrue();
        assertThat(passengerCell.isBusy()).isTrue();
        assertThat(passengerCell.input(passengerCar)).isFalse();
        assertThat(truckCell.isBusy()).isFalse();
        assertThat(truckCell.input(passengerCar)).isFalse();
        assertThat(truckCell.isBusy()).isFalse();
        assertThat(truckCell.input(truckCar)).isTrue();
        assertThat(truckCell.isBusy()).isTrue();
        assertThat(truckCell.input(truckCar)).isFalse();
        assertThat(passengerCell.output(truckCar)).isFalse();
        assertThat(passengerCell.isBusy()).isTrue();
        assertThat(passengerCell.output(passengerCar)).isTrue();
        assertThat(passengerCell.isBusy()).isFalse();
        assertThat(truckCell.output(passengerCar)).isFalse();
        assertThat(truckCell.isBusy()).isTrue();
        assertThat(truckCell.output(truckCar)).isTrue();
        assertThat(truckCell.isBusy()).isFalse();
    }

    @Test
    public void whenPakrCar() {
        Parking parking = new UndergroundParking(1, 3);
        assertThat(parking.getBusyCellsCount()).isEqualTo(0);
        assertThat(parking.getUnbusyCellsCount()).isEqualTo(4);
    }
}