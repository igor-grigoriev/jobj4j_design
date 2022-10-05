package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short sh = 2;
        int i = 3;
        long l = 4L;
        float f = 5f;
        double d = 6d;
        char ch = 'c';
        boolean bl = false;
        LOG.info("byte {}, short {}, int {}, long {}, float {}, double {}, char {}, boolean {}",
                b, sh, i, l, f, d, ch, bl);
    }
}