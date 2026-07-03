package com.pig4cloud.pig.common.security.filter;

import java.util.HashMap;
import java.util.Map;

public class FeignRequestContext {
    private static final ThreadLocal<Map<String, String>> CONTEXT = ThreadLocal.withInitial(HashMap::new);

    public static Map<String, String> getAll() {
        return new HashMap<>(CONTEXT.get());
    }

    public static void setAll(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            CONTEXT.get().putAll(map);
        }
    }

    public static void clear() {
        CONTEXT.remove();
    }
    public static String getClientId() {
        return CONTEXT.get().get(com.pig4cloud.pig.common.core.constant.CommonConstants.CLIENT);
    }
    public static void setClientId(String val){
        CONTEXT.get().put(com.pig4cloud.pig.common.core.constant.CommonConstants.CLIENT, val);
    }
}
