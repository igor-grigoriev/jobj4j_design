package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<String>> properties = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            properties.merge(new FileProperty(file.toFile().length(), file.getFileName().toString()),
                    new ArrayList<>(Collections.singleton(file.toAbsolutePath().toString())), (a, b) -> {
                a.addAll(b); return a; });
        }
        return super.visitFile(file, attrs);
    }
}