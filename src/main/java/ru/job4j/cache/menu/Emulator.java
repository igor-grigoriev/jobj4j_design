package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    private static Scanner scanner = new Scanner(System.in);
    private static DirFileCache dirFileCache;

    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            showMenu();
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

    private static void showMenu() {
        System.out.println("Меню:");
        System.out.println("1. Указать кэшируемую директорию");
        System.out.println("2. Загрузить содержимое файла в кэш");
        System.out.println("3. Получить содержимое файла из кэша");
        System.out.println("4. Выход");
        System.out.print("Select: ");
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
            if (Files.exists(Paths.get(dirFileCache.getCachingDir() + FileSystems.getDefault().getSeparator() + fileName))) {
                dirFileCache.load(fileName);
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
            if (Files.exists(Paths.get(dirFileCache.getCachingDir() + FileSystems.getDefault().getSeparator() + fileName))) {
                System.out.println(dirFileCache.get(fileName));
            } else {
                System.out.println("Указанный файл не существует");
            }
        } else {
            System.out.println("Сначала укажите кэшируемую директорию");
        }
    }
}