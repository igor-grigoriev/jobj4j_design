package ru.job4j.gc.leak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {
    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    public static final String SEPARATOR = System.lineSeparator();
    public static final int COUNT = 50;

    private final List<Comment> comments = new ArrayList<>();
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        phrases = read(PATH_PHRASES);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            comments.add(new Comment(String.join(SEPARATOR, List.of(phrases.get(random.nextInt(phrases.size())),
                    phrases.get(random.nextInt(phrases.size())), phrases.get(random.nextInt(phrases.size())))),
                    userGenerator.randomUser()));
        }
    }
}