package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    public void whenControlQuality() {
        Food food = new Milk("milk", 10, 1, LocalDate.now().minusDays(2), LocalDate.now().minusDays(1));
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(Collections.singletonList(trash));
        controlQuality.control(food);
        assertThat(trash.foods).contains(food);
    }
}