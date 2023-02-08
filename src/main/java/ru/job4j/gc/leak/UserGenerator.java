package ru.job4j.gc.leak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {
    private static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";
    private static final String SEPARATOR = " ";
    private static final int NEW_USERS = 1000;

    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            users.add(new User(String.join(SEPARATOR, List.of(surnames.get(random.nextInt(surnames.size())),
                    names.get(random.nextInt(names.size())), patrons.get(random.nextInt(patrons.size()))))));
        }
    }

    private void readAll() {
        names = read(PATH_NAMES);
        surnames = read(PATH_SURNAMES);
        patrons = read(PATH_PATRONS);
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}