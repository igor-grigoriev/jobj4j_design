package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key) || values.get(key).isBlank()) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    public int size() {
        return values.size();
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] ar = arg.split("=", 2);
            if (ar.length != 2 || ar[0].isBlank() || ar[1].isBlank()
                    || "-".equals(ar[0]) || !ar[0].contains("-")) {
                throw new IllegalArgumentException();
            }
            values.putIfAbsent(ar[0].substring(1), ar[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}