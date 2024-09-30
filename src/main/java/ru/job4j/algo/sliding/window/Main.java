package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Event implements Comparable<Event> {
    int time;
    boolean isStart;

    Event(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(Event other) {
        if (this.time == other.time) {
            return this.isStart ? -1 : 1;
        }
        return Integer.compare(this.time, other.time);
    }
}

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        List<Event> events = new ArrayList<>();
        for (Interval interval : intervals) {
            events.add(new Event(interval.start, true));
            events.add(new Event(interval.end, false));
        }
        events.sort(Event::compareTo);
        int count = 0;
        int max = 0;
        int start = -1;
        int end = -1;
        for (Event event : events) {
            count = event.isStart ? count + 1 : count - 1;
            if (count > max) {
                max = count;
                start = event.time;
            }
        }
        if (start != -1) {
            for (Event event : events) {
                if (!event.isStart && start < event.time) {
                    end = event.time;
                    break;
                }
            }
        }
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }

}