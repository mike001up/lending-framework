package com.pig4cloud.pig.common.excel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelConfig {

    @Bean
    public TimestampStringConverter timestampStringConverter() {
        return new TimestampStringConverter();
    }

}