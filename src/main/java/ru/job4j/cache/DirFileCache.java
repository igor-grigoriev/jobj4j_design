package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    public String load(String key) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(cachingDir + FileSystems.getDefault().getSeparator() + key))) {
            reader.lines().forEach(sb::append);
            this.put(key, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getCachingDir() {
        return cachingDir;
    }
}