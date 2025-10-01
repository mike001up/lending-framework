package com.pig4cloud.pig.common.core.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class QueryWrapperBuilder {

    /**
     * 构建查询条件（支持等值、模糊、范围查询）
     */
    public static <T> QueryWrapper<T> build(T queryObj, String[] likeFields, String[] rangeFields) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (queryObj == null) {
            return wrapper;
        }

        try {
            // 等值查询
            buildEqualConditions(wrapper, queryObj, likeFields, rangeFields);

            // 模糊查询
            buildLikeConditions(wrapper, queryObj, likeFields);

            // 范围查询
            buildRangeConditions(wrapper, queryObj, rangeFields);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return wrapper;
    }

    /**
     * 构建查询条件（只有模糊查询）
     */
    public static <T> QueryWrapper<T> build(T queryObj, String[] likeFields) {
        return build(queryObj, likeFields, new String[0]);
    }

    /**
     * 等值查询（排除模糊和范围字段）
     */
    private static <T> void buildEqualConditions(QueryWrapper<T> wrapper, T queryObj, String[] likeFields, String[] rangeFields) throws Exception {
        Field[] fields = getAllFields(queryObj.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();

            // ⚠️ 跳过无关字段
            if ("serialVersionUID".equals(fieldName) || "entityClass".equals(fieldName) || "class".equals(fieldName)) {
                continue;
            }

            Object value = field.get(queryObj);
            if (value != null) {
                if (!ArrayUtils.contains(likeFields, fieldName) && !ArrayUtils.contains(rangeFields, fieldName)) {
                    wrapper.eq(convertFieldName(fieldName), value);
                }
            }
        }
    }

    /**
     * 模糊查询
     */
    private static <T> void buildLikeConditions(QueryWrapper<T> wrapper, T queryObj, String[] likeFields) throws Exception {
        if (likeFields == null) return;
        for (String fieldName : likeFields) {
            Field field = getField(queryObj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                Object value = field.get(queryObj);
                if (value instanceof String && StringUtils.isNotBlank((String) value)) {
                    wrapper.like(convertFieldName(fieldName), value);
                }
            }
        }
    }

    /**
     * 范围查询（xxxStart/xxxEnd）
     */
    private static <T> void buildRangeConditions(QueryWrapper<T> wrapper, T queryObj, String[] rangeFields) throws Exception {
        if (rangeFields == null) return;

        Map<String, RangeField> rangeMap = new HashMap<>();

        for (String fieldName : rangeFields) {
            Field field = getField(queryObj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                Object value = field.get(queryObj);
                if (value != null) {
                    if (fieldName.endsWith("Start") || fieldName.endsWith("Begin")) {
                        String base = fieldName.replace("Start", "").replace("Begin", "");
                        rangeMap.computeIfAbsent(base, k -> new RangeField()).startValue = value;
                    } else if (fieldName.endsWith("End") || fieldName.endsWith("Finish")) {
                        String base = fieldName.replace("End", "").replace("Finish", "");
                        rangeMap.computeIfAbsent(base, k -> new RangeField()).endValue = value;
                    }
                }
            }
        }

        for (Map.Entry<String, RangeField> entry : rangeMap.entrySet()) {
            String baseField = entry.getKey();
            RangeField rf = entry.getValue();
            String dbField = convertFieldName(baseField);

            if (rf.startValue != null && rf.endValue != null) {
                wrapper.between(dbField, rf.startValue, rf.endValue);
            } else if (rf.startValue != null) {
                wrapper.ge(dbField, rf.startValue);
            } else if (rf.endValue != null) {
                wrapper.le(dbField, rf.endValue);
            }
        }
    }

    /**
     * 范围字段包装
     */
    private static class RangeField {
        Object startValue;
        Object endValue;
    }

    /**
     * 驼峰转下划线
     */
    private static String convertFieldName(String fieldName) {
        return fieldName.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    /**
     * 获取字段（包含父类）
     */
    private static Field getField(Class<?> clazz, String name) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }

    /**
     * 获取类的所有字段（包含父类）
     */
    private static Field[] getAllFields(Class<?> clazz) {
        Map<String, Field> map = new HashMap<>();
        while (clazz != null) {
            for (Field f : clazz.getDeclaredFields()) {
                map.putIfAbsent(f.getName(), f);
            }
            clazz = clazz.getSuperclass();
        }
        return map.values().toArray(new Field[0]);
    }
}
