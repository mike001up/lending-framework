package com.pig4cloud.pig.admin.api.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemFlag {
    NON_SYSTEM_BUILT_IN("NON_SYSTEM_BUILT_IN", "非系统内置"),
    SYSTEM_BUILT_IN("SYSTEM_BUILT_IN", "系统内置");

    private final String code;
    private final String desc;
}