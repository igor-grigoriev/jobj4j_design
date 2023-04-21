package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Collections;
import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenControlQuality() {
        Food food = new Milk("milk", 10, 1, LocalDate.now().minusDays(2), LocalDate.now().plusDays(3));
        Shop shop = new Shop();
        ControlQuality controlQuality = new ControlQuality(Collections.singletonList(shop));
        controlQuality.control(food);
        assertThat(shop.foods).contains(food);
    }
}