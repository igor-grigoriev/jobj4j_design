package ru.job4j.io;

import java.io.FileOutputStream;

public class WriteFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("2 x 2 = 4".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2 x 3 = 6".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2 x 4 = 8".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2 x 5 = 10".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2 x 6 = 12".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2 x 7 = 14".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2 x 8 = 16".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("2 x 9 = 18".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}