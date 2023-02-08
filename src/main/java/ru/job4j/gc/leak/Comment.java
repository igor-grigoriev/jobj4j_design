package ru.job4j.gc.leak;

public class Comment {
    private final String text;
    private final User user;

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
    }
}