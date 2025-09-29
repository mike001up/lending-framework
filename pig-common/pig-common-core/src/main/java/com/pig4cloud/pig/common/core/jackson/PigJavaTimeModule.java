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
package com.pig4cloud.pig.common.core.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * java 8 时间默认序列化
 *
 * @author L.cm
 * @author lishanbu
 */

public class PigJavaTimeModule extends SimpleModule {

	public PigJavaTimeModule() {
		super(PackageVersion.VERSION);

		// ======================= 时间序列化规则 ===============================
		// LocalDateTime -> 时间戳（毫秒）
		this.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
			@Override
			public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeNumber(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
			}
		});

		// LocalDate -> 字符串（yyyy-MM-dd）
		this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
		// LocalTime -> 字符串（HH:mm:ss）
		this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
		// Instant -> 默认序列化
		this.addSerializer(Instant.class, InstantSerializer.INSTANCE);
		// Duration -> 默认序列化
		this.addSerializer(Duration.class, DurationSerializer.INSTANCE);

		// ======================= 时间反序列化规则 ==============================
		// 时间戳（毫秒） -> LocalDateTime
		this.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
				long timestamp = p.getLongValue();
				return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
			}
		});

		// yyyy-MM-dd -> LocalDate
		this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
		// HH:mm:ss -> LocalTime
		this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));
		// Instant -> 默认反序列化
		this.addDeserializer(Instant.class, InstantDeserializer.INSTANT);
		// Duration -> 默认反序列化
		this.addDeserializer(Duration.class, DurationDeserializer.INSTANCE);
	}

}
