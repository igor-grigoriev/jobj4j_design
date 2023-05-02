package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }

    public List<Store> getsStores() {
        return stores;
    }

    public void restore() {
        for (Store store : stores) {
            List<Food> foods = new ArrayList<>(((AbstractStore) store).foods);
            for (Store controlStore : stores) {
                if (!store.equals(controlStore)) {
                    for (Food food : foods) {
                        if (controlStore.add(food)) {
                            store.delete(food);
                        }
                    }
                }
            }
        }
    }
}