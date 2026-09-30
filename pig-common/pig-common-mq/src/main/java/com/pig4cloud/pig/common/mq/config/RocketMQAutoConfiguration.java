package com.pig4cloud.pig.common.mq.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pig4cloud.pig.common.mq.producer.MessageProducer;



@Configuration
@ConditionalOnClass(StreamBridge.class)
public class RocketMQAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MessageProducer messageProducer(StreamBridge streamBridge) {
        return new MessageProducer(streamBridge);
    }
}