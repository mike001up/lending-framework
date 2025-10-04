package com.pig4cloud.pig.common.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final AtomicInteger SEQ = new AtomicInteger(0);
    private static volatile String currentDate = LocalDate.now().format(DATE_FMT);

    // 从 JVM 启动参数获取机器号，没有就默认 "01"
    private static final String MACHINE_ID = String.format("%02d",
            Integer.parseInt(System.getProperty("MACHINE_ID", "1")));

    private IdGenerator() {}

    public static String nextId() {
        String today = LocalDate.now().format(DATE_FMT);

        if (!today.equals(currentDate)) {
            synchronized (IdGenerator.class) {
                if (!today.equals(currentDate)) {
                    currentDate = today;
                    SEQ.set(0);
                }
            }
        }

        int n = SEQ.incrementAndGet();
        return today + MACHINE_ID + String.format("%05d", n);
    }

}

