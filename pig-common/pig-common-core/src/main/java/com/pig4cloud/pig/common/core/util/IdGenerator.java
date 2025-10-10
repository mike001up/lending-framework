package com.pig4cloud.pig.common.core.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;


public class IdGenerator {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis() % 100000);
    private static final String MACHINE_ID = System.getProperty("MACHINE_ID", "01");

    public static synchronized String nextId() {
        String today = LocalDate.now().format(DATE_FMT);
        long n = SEQ.incrementAndGet();
        return today + MACHINE_ID + String.format("%05d", n % 100000);
    }
}

