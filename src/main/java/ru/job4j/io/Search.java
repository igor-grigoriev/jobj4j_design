package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith(".js")).forEach(System.out::println);
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