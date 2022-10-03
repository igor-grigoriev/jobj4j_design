package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> logList = new ArrayList<>();
        List<String> answers = readPhrases();
        Random random = new Random();
        boolean stop = false;
        var scanner = new Scanner(new InputStreamReader(System.in));
        while (scanner.hasNext()) {
            String str = scanner.next();
            if (OUT.equals(str)) {
                logList.add(str);
                saveLog(logList);
                break;
            }
            if (STOP.equals(str)) {
                stop = true;
            }
            if (CONTINUE.equals(str)) {
                stop = false;
            }
            logList.add(str);
            if (!stop) {
                String answer = answers.get(random.nextInt(answers.size()));
                logList.add(answer);
                System.out.println(answer);
            }
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}