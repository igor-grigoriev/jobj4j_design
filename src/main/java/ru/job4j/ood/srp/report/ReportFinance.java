package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportFinance implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Currency currency;

    public ReportFinance(Store store, DateTimeParser<Calendar> dateTimeParser, Currency currency) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currency = currency;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(new InMemoryCurrencyConverter().convert(Currency.RUB, employee.getSalary(), currency))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}