package ru.job4j.ood.tdd;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cinema3D implements Cinema {
    private final List<Session> sessions = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return sessions.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        Calendar nowDate = Calendar.getInstance();
        nowDate.set(Calendar.HOUR_OF_DAY, nowDate.get(Calendar.HOUR_OF_DAY) - 1);
        if (row < 0 || column < 0 || date.before(nowDate)) {
            throw new IllegalArgumentException();
        }
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}