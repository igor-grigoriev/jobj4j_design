package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    private static final int ADD_ROOT = 1;
    private static final int ADD_CHILD = 2;
    private static final int DO_ACTION = 3;
    private static final int PRINT_MENU = 4;
    private static final int EXIT = 5;
    private static final String MENU = """
                Меню:
                1. Добавить элемент в корень меню
                2. Добавить элемент к родительскому элементу
                3. Вызвать действие, привязанное к пункту меню
                4. Вывести меню в консоль
                5. Выход
                "Выбор: "
            """;
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    private static Scanner scanner = new Scanner(System.in);
    private static Menu menu = new SimpleMenu();

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.print(MENU);
            int select = Integer.parseInt(scanner.nextLine());
            if (select < ADD_ROOT || select > EXIT) {
                System.out.println("Не выбран ни один из пунктов меню");
                continue;
            }
            switch (select) {
                case ADD_ROOT: addRoot();
                    break;
                case ADD_CHILD: addChild();
                    break;
                case DO_ACTION: doAction();
                    break;
                case PRINT_MENU: printMenu();
                    break;
                case EXIT: run = false;
                    break;
                default: run = false;
                    break;
            }
        }
    }

    private static void addChild() {
        System.out.print("Укажите имя родительского элемента: ");
        String parentName = scanner.nextLine();
        System.out.print("Укажите имя добавляемого элемента: ");
        String childName = scanner.nextLine();
        if (menu.add(parentName, childName, DEFAULT_ACTION)) {
            System.out.println(String.format("Элемент '%s' успешно добавлен к родителю '%s'", childName, parentName));
        } else {
            System.out.println(String.format("Элемент '%s' не добавлен к родителю '%s'", childName, parentName));
        }
    }

    private static void addRoot() {
        System.out.print("Укажите имя добавляемого элемента: ");
        String itemName = scanner.nextLine();
        if (menu.add(Menu.ROOT, itemName, DEFAULT_ACTION)) {
            System.out.println(String.format("Элемент '%s' успешно добавлен в корень меню", itemName));
        } else {
            System.out.println(String.format("Элемент '%s' не добавлен в корень меню", itemName));
        }
    }

    private static void doAction() {
        System.out.print("Укажите имя элемента, действие которого необходимо вызвать: ");
        String itemName = scanner.nextLine();
        Optional<Menu.MenuItemInfo> itemInfo = menu.select(itemName);
        if (itemInfo.isPresent()) {
            itemInfo.get().getActionDelegate().delegate();
        } else {
            System.out.println(String.format("Элемент '%s' не найден в меню", itemName));
        }
    }

    private static void printMenu() {
        new SimpleMenuPrinter().print(menu);
    }
}