package com.pig4cloud.pig.common.core.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class MqttMessage {
    //userId必须为string类型,前端过滤比较好,不要改成其他类型
    private String userId;
    private String msgType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String timestamp;
    private Object content;
}
