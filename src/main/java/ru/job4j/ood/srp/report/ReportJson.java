package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private final Store store;
    private final Gson lib;

    public ReportJson(Store store) {
        this.store = store;
        lib = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return lib.toJson(store.findBy(filter));
    }
}