package com.pig4cloud.pig.common.core.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;

public class DateTimeUtil {

    public static Timestamp startOfDay(LocalDate date) {
        return Timestamp.valueOf(date.atStartOfDay());
    }

    public static Timestamp endOfDay(LocalDate date) {
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return Timestamp.valueOf(endOfDay);
    }

    public static boolean greaterOrEqual(LocalDate d1, LocalDate d2) {
        return d1.isAfter(d2) || d1.isEqual(d2);
    }

    public static Instant getLastMomentOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth())
                .atTime(23, 59, 59)
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }

    public static Instant getFirstMomentOfMonth(LocalDate date) {
        return date.withDayOfMonth(1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();
    }

    public static Timestamp now() {
        return Timestamp.from(Instant.now());
    }

}
