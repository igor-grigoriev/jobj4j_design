package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    private static final String MENU1 = "1. Указать кэшируемую директорию\n";
    private static final String MENU2 = "2. Загрузить содержимое файла в кэш\n";
    private static final String MENU3 = "3. Получить содержимое файла из кэша\n";
    private static final String MENU4 = "4. Выход\n";
    private static final String MENU = "Меню:\n" + MENU1 + MENU2 + MENU3 + MENU4 + "Выбор: ";
    private static Scanner scanner = new Scanner(System.in);
    private static DirFileCache dirFileCache;

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.print(MENU);
            int select = Integer.parseInt(scanner.nextLine());
            if (select < 1 || select > 4) {
                System.out.println("Не выбран ни один из пунктов меню");
                continue;
            }
            switch (select) {
                case 1: selectDir();
                    break;
                case 2: loadFile();
                    break;
                case 3: getFile();
                    break;
                case 4: run = false;
                    break;
                default: run = false;
                    break;
            }
        }
    }

    private static void selectDir() {
        System.out.print("Укажите кэшируемую директорию: ");
        String cachingDir = scanner.nextLine();
        if (Files.isDirectory(Paths.get(cachingDir))) {
            dirFileCache = new DirFileCache(cachingDir);
        } else {
            System.out.println("Указанная директория не существует");
        }
    }

    private static void loadFile() {
        if (dirFileCache != null) {
            System.out.print("Укажите имя файла: ");
            String fileName = scanner.nextLine();
            if (Files.exists(Path.of(dirFileCache.getCachingDir(), fileName))) {
                dirFileCache.get(fileName);
                System.out.println("Содержимое указанного файла успешно загружено в кэш");
            } else {
                System.out.println("Указанный файл не существует");
            }
        } else {
            System.out.println("Сначала укажите кэшируемую директорию");
        }
    }

    private static void getFile() {
        if (dirFileCache != null) {
            System.out.print("Укажите имя файла: ");
            String fileName = scanner.nextLine();
            if (Files.exists(Path.of(dirFileCache.getCachingDir(), fileName))) {
                System.out.println(dirFileCache.get(fileName));
            } else {
                System.out.println("Указанный файл не существует");
            }
        } else {
            System.out.println("Сначала укажите кэшируемую директорию");
        }
    }
}