package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        visitor.properties.forEach((key, value) -> {
            if (value.size() > 1) {
                System.out.printf("%s - %d%n", key.getName(), key.getSize());
                value.forEach(System.out::println);
            }
        });
    }
}