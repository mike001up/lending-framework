package com.pig4cloud.pig.common.mq.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 所有 MQ 消息的基类，包含公共头信息
 */
@Data
@NoArgsConstructor
public class BaseMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 全局追踪 ID（从 MDC 获取）
     */
    private String traceId;

    /**
     * 消息发送时间戳
     */
    private Long timestamp;

    /**
     * 业务事件类型（如 "LOGIN_FAILURE"）
     */
    private String eventType;

    /**
     * 业务负载数据
     */
    private T payload;
    
    // 新增：消息 Tag（可选）
    private String tag;
    // 新增：消息 Key（可选）
    private String key;

    public BaseMessage(String eventType, T payload) {
        this.eventType = eventType;
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }
}