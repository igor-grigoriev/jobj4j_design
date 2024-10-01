package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List visitTimes) {
        int maxLoadStartTime = -1;
        int maxLoadEndTime = -1;
        int max = 0;
        int count = 0;
        List<Event> events = new ArrayList<>();
        for (Object visitTime : visitTimes) {
            int[] array = (int[]) visitTime;
            events.add(new Event(array[0], EventType.ARRIVAL));
            events.add(new Event(array[1], EventType.DEPARTURE));
        }
        events.sort(Event::compareTo);
        for (Event event : events) {
            count = EventType.ARRIVAL.equals(event.type) ? count + 1 : count - 1;
            if (count > max) {
                max = count;
                maxLoadStartTime = event.time;
            }
        }
        if (maxLoadStartTime != -1) {
            for (Event event : events) {
                if (!EventType.ARRIVAL.equals(event.type) && maxLoadStartTime < event.time) {
                    maxLoadEndTime = event.time;
                    break;
                }
            }
        }
        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}