//package com.pig4cloud.pig.admin.consumer;
//
//import com.pig4cloud.pig.common.core.constant.MqConstant;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@RocketMQMessageListener(topic = MqConstant.CESHI, consumerGroup = MqConstant.PIG_CONSUMER_GROUP)
//@Slf4j
//public class MsgCeshiConsumer implements RocketMQListener<String> {
//
//    @Override
//    public void onMessage(String message) {
//        log.info("收到消息: " + message);
//    }
//}
