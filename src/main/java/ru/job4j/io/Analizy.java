package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                String[] ar = str.split(" ");
                if (sb.isEmpty() && ("400".equals(ar[0]) || "500".equals(ar[0]))) {
                    sb.append(ar[1]);
                }
                if (!sb.isEmpty() && !"400".equals(ar[0]) && !"500".equals(ar[0])) {
                    sb.append(String.format(";%s%n", ar[1]));
                    out.print(sb);
                    sb = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}