package com.pig4cloud.pig.common.core.config;

import com.pig4cloud.pig.common.core.util.TimestampStringConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelConfig {

    // 比如注册自定义的转换器
    @Bean
    public TimestampStringConverter timestampStringConverter() {
        return new TimestampStringConverter();
    }

}

