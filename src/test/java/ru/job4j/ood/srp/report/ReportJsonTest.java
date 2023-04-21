package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.*;

class ReportJsonTest {
    @Test
    public void whenGenerated() {
        MemStore store = new MemStore();
        Calendar hired = new Calendar.Builder().setDate(2023, 3, 1).build();
        Calendar fired = new Calendar.Builder().setDate(2023, 3, 30).build();
        Employee worker = new Employee("Ivan", hired, fired, 100);
        store.add(worker);
        Report json = new ReportJson(store);
        String expect = "[{\"name\":\"Ivan\",\"hired\":{\"year\":2023,\"month\":4,\"dayOfMonth\":1,\"hourOfDay\":0,\"minute\":0,\"second\":0}," +
                "\"fired\":{\"year\":2023,\"month\":4,\"dayOfMonth\":30,\"hourOfDay\":0,\"minute\":0,\"second\":0},\"salary\":100.0}]";
        assertThat(json.generate(em -> true)).isEqualTo(expect);
    }
}