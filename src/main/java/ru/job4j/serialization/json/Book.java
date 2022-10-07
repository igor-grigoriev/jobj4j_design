package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Book {
    private final String name;
    private final int size;
    private final boolean child;
    private final String[] authors;
    private final Publisher publisher;

    public Book(String name, int size, boolean child, String[] authors, Publisher publisher) {
        this.name = name;
        this.size = size;
        this.child = child;
        this.authors = authors;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{name=" + name + ", size=" + size + ", child=" + child
                + ", authors=" + Arrays.toString(authors) + ", publisher=" + publisher + "}";
    }

    public static void main(String[] args) {
        final Book book = new Book("Kolobok", 9, true,
                new String[] {"Ivan", "Igor"}, new Publisher("Moscow", 2022));

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(book));

        final Book bookMod = gson.fromJson(gson.toJson(book), Book.class);
        System.out.println(bookMod);
    }

    private static class Publisher {
        String name;
        int year;

        private Publisher(String name, int year) {
            this.name = name;
            this.year = year;
        }

        @Override
        public String toString() {
            return "Publisher{name=" + name + ", year=" + year + "}";
        }
    }
}