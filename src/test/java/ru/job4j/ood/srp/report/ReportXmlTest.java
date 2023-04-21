package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import javax.xml.bind.JAXBException;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.*;

class ReportXmlTest {
    @Test
    public void whenGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar hired = new Calendar.Builder().setDate(2023, 3, 1).build();
        Calendar fired = new Calendar.Builder().setDate(2023, 3, 30).build();
        Employee worker = new Employee("Ivan", hired, fired, 100);
        store.add(worker);
        Report xml = new ReportXml(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><employees><employees><fired>2023-04-30T00:00:00+03:00</fired>"
                + "<hired>2023-04-01T00:00:00+03:00</hired><name>Ivan</name><salary>100.0</salary></employees></employees>";
        assertThat(xml.generate(em -> true)).isEqualTo(expect);
    }
}