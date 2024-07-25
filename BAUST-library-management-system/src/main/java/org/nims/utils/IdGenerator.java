package org.nims.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
        private static final AtomicInteger counter = new AtomicInteger(0);

        public static int generateId() {
            return counter.incrementAndGet();
        }
    }

