package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            for (String s : text.toString().split(System.lineSeparator())) {
                int number = Integer.parseInt(s);
                System.out.println((number % 2 == 0) ? number + " четное" : number + " нечетное");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}