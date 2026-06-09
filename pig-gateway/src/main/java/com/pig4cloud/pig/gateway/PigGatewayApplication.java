/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

/**
 * @author lengleng
 * @date 2018年06月21日
 * <p>
 * 网关应用
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.pig4cloud.pig.common.core","com.pig4cloud.pig"},
		excludeFilters = {
			@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.pig4cloud\\.pig\\.common\\.mybatis\\..*"),
			@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.pig4cloud\\.pig\\.common\\.feign\\.sentinel\\.handle\\..*"),
			@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.pig4cloud\\.pig\\.common\\.core\\.config\\.ExcelConfig"),
			@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.pig4cloud\\.pig\\.common\\.core\\.util\\.TimestampStringConverter")
		})
@EnableFeignClients(basePackages = {"com.pig4cloud.pig.gateway.fegin", "com.pig4cloud.pig.admin.api.feign"})
@Import(FeignAutoConfiguration.class)
public class PigGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigGatewayApplication.class, args);
	}
}
