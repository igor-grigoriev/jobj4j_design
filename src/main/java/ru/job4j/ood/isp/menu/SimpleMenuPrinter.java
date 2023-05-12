package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(i.getNumber().replaceAll(".", " ") + i.getNumber() + i.getName()));
    }

    public String menuToStr(Menu menu) {
        StringBuilder sb = new StringBuilder();
        menu.forEach(i -> sb.append(i.getNumber().replaceAll(".", " "))
                .append(i.getNumber()).append(i.getName()).append(System.lineSeparator()));
        return sb.toString();
    }
}