package com.pig4cloud.pig.common.core.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Instant;

public class DateTimeUtil {

    /**
     * 获取某天的开始时间（yyyy-MM-dd 00:00:00）
     */
    public static Timestamp startOfDay(LocalDate date) {
        return Timestamp.valueOf(date.atStartOfDay());
    }

    /**
     * 获取某天的结束时间（yyyy-MM-dd 23:59:59）
     */
    public static Timestamp endOfDay(LocalDate date) {
        Instant endOfDay = date.atTime(23, 59, 59);
        return Timestamp.valueOf(endOfDay);
    }

    /**
     * isAfter() ：是否晚于某日期
     *
     * isBefore() ：是否早于某日期
     *
     * isEqual() ：是否等于某日期
     *
     * (greaterOrEqual(d1, d2))  d1 大于等于 d2
     */
    public static boolean greaterOrEqual(LocalDate d1, LocalDate d2) {
        return d1.isAfter(d2) || d1.isEqual(d2);
    }


    /**
     * 获取某月最后一秒的 Instant
     * @param date 任意该月中的日期
     * @return Instant -> 当月最后一天 23:59:59
     */
    public static Instant getLastMomentOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth())
                .atTime(23, 59, 59);
    }

    /**
     * 获取某月第一秒的 Instant
     * @param date 任意该月中的日期
     * @return Instant -> 当月第一天 00:00:00
     */
    public static Instant getFirstMomentOfMonth(LocalDate date) {
        return date.withDayOfMonth(1).atStartOfDay();
    }

    /**
     * 获取当前时间
     */
    public static Timestamp now() {
        return Timestamp.valueOf(Instant.now());
    }

}
