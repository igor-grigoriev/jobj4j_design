package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> prev = new HashMap<>();
        previous.forEach(it -> prev.put(it.getId(), it));
        Map<Integer, User> cur = new HashMap<>();
        current.forEach(it -> cur.put(it.getId(), it));
        for (Map.Entry<Integer, User> user : prev.entrySet()) {
            if (cur.containsKey(user.getKey())) {
                if (!cur.get(user.getKey()).equals(user.getValue())) {
                    changed++;
                }
            } else {
                deleted++;
            }
        }
        for (Map.Entry<Integer, User> user : cur.entrySet()) {
            if (!prev.containsKey(user.getKey())) {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}