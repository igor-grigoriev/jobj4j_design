package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Count of arguments should be 2");
        }
        File file = Paths.get(args[0]).toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException(String.format("Invalid argument number 2 %s", args[1]));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static class SearchFiles extends SimpleFileVisitor<Path> {
        Predicate<Path> condition;
        List<Path> paths = new ArrayList<>();

        private SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);
            if (condition.test(file)) {
                paths.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        private List<Path> getPaths() {
            return paths;
        }
    }
}