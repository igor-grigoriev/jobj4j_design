package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder sb = new StringBuilder();
            for (String str : in.lines().collect(Collectors.toList())) {
                String[] ar = str.split(" ");
                if (sb.isEmpty() && (ar[0].equals("400") || ar[0].equals("500"))) {
                    sb.append(ar[1]);
                }
                if (!sb.isEmpty() && !ar[0].equals("400") && !ar[0].equals("500")) {
                    sb.append(String.format(";%s%n", ar[1]));
                    out.print(sb);
                    sb = new StringBuilder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}