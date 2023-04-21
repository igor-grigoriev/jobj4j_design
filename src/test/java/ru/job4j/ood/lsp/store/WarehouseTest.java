package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    public void whenControlQuality() {
        Food food = new Milk("milk", 10, 1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(9));
        Warehouse warehouse = new Warehouse();
        ControlQuality controlQuality = new ControlQuality(Collections.singletonList(warehouse));
        controlQuality.control(food);
        assertThat(warehouse.foods).contains(food);
    }
}