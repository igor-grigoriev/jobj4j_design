package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path patch : sources) {
                zip.putNextEntry(new ZipEntry(patch.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(patch.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName jvm = ArgsName.of(args);
        validateArgs(jvm);
        zip.packFiles(Search.search(Paths.get(jvm.get("d")), it -> it.toFile().getName().endsWith(jvm.get("e"))), new File(jvm.get("o")));
    }

    private static void validateArgs(ArgsName args) {
        if (args.size() != 3) {
            throw new IllegalArgumentException("Count of arguments should be 3");
        }
        File file = Paths.get(args.get("d")).toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!args.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Invalid argument number 2 %s", args.get("e")));
        }
        if (!args.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Invalid argument number 3 %s", args.get("e")));
        }
    }
}