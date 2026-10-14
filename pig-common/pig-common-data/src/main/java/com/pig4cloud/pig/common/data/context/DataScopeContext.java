package com.pig4cloud.pig.common.data.context;

/**
 * 数据权限 SQL 上下文
 * AOP 切面写入，MyBatis 拦截器读取
 */
public class DataScopeContext {

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static void set(String sql) { HOLDER.set(sql); }
    public static String get() { return HOLDER.get(); }
    public static void clear() { HOLDER.remove(); }
}