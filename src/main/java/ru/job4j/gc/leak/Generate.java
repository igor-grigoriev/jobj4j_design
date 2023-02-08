package ru.job4j.gc.leak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public interface Generate {
    void generate();

    default List<String> read(String path) {
        List<String> text = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(text::add);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return text;
    }
}