package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class FileSearcher {
    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        Path start = Paths.get(argsName.get("d"));
        Predicate<Path> predicate = switch (argsName.get("t")) {
            case "name" -> path -> argsName.get("n").equals(path.toFile().getName());
            case "mask" -> path -> path.toFile().getName().matches(argsName.get("n")
                    .replaceAll("\\.", "\\\\\\.").replaceAll("\\*", ".*")
                    .replaceAll("\\?", ".{1}"));
            case "regex" -> path -> path.toFile().getName().matches(argsName.get("n"));
            default -> null;
        };
        try (FileOutputStream out = new FileOutputStream(argsName.get("o"))) {
            for (Path path : Search.search(start, predicate)) {
                out.write(path.toString().getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(ArgsName argsName) {
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Count of arguments should be 4");
        }
        File file = Paths.get(argsName.get("d")).toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        argsName.get("n");
        String type = argsName.get("t");
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Illegal argument t - type of search must be name, mask or regex");
        }
        argsName.get("o");
    }
}