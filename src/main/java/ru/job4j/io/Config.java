package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(it -> {
                if (!it.isEmpty() && !it.startsWith("#")) {
                    String key = (it.indexOf("=") > 0) ? it.substring(0, it.indexOf("=")) : "";
                    String value = (it.indexOf("=") > 0) ? it.substring(it.indexOf("=") + 1) : "";
                    if (key.isBlank() || value.isBlank()) {
                        throw new IllegalArgumentException("Ошибка в строке " + it);
                    }
                    values.putIfAbsent(key, value);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}