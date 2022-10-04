package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validateArgs(argsName);
        List<Integer> indexes = new ArrayList<>();
        String[] columns = argsName.get("filter").split(",");
        try (Scanner scan = new Scanner(new FileReader(argsName.get("path")));
             PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("out")))) {
            if (scan.hasNext()) {
                StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
                List<String> rows = Arrays.asList(scan.next().split(argsName.get("delimiter")));
                for (String column : columns) {
                    if (rows.contains(column)) {
                        indexes.add(rows.indexOf(column));
                        joiner.add(rows.get(rows.indexOf(column)));
                    }
                }
                pw.println(joiner);
            }
            while (scan.hasNext()) {
                StringJoiner joiner = new StringJoiner(argsName.get("delimiter"));
                List<String> rows = Arrays.asList(scan.next().split(argsName.get("delimiter")));
                for (Integer index : indexes) {
                    joiner.add(rows.get(index));
                }
                pw.println(joiner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName args) {
        if (args.size() != 4) {
            throw new IllegalArgumentException("Count of arguments should be 4");
        }
        if (!args.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("Invalid argument number 1 %s", args.get("path")));
        }
        args.get("delimiter");
        if (!args.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("Invalid argument number 3 %s", args.get("out")));
        }
        args.get("filter");
    }
}