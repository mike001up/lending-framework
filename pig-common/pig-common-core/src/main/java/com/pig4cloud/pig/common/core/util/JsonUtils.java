package com.pig4cloud.pig.common.core.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

    /**
     * 任意对象转 JSONObject
     * @param obj 目标对象
     * @return JSONObject 对象
     */
    public static JSONObject toJsonObject(Object obj) {
        if (obj == null) {
            return new JSONObject();
        }
        return (JSONObject) JSONObject.toJSON(obj);
    }
}
