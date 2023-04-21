package ru.job4j.ood.lsp.store;

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
}