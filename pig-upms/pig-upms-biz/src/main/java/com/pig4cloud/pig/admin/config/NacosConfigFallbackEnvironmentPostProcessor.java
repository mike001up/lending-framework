package com.pig4cloud.pig.admin.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class NacosConfigFallbackEnvironmentPostProcessor implements EnvironmentPostProcessor {

	private static final AtomicBoolean LOADED = new AtomicBoolean(false);

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		if (LOADED.getAndSet(true)) {
			return;
		}

		String serverAddr = resolve(environment, "spring.cloud.nacos.config.server-addr");
		String namespace = resolve(environment, "spring.cloud.nacos.config.namespace");
		String username = resolve(environment, "spring.cloud.nacos.config.username");
		String password = resolve(environment, "spring.cloud.nacos.config.password");

		if (!StringUtils.hasText(serverAddr)) {
			return;
		}

		String appName = resolve(environment, "spring.application.name");
		String[] profiles = environment.getActiveProfiles();
		String fileExtension = resolve(environment, "spring.cloud.nacos.config.file-extension");
		if (!StringUtils.hasText(fileExtension)) {
			fileExtension = "yml";
		}

		Properties props = new Properties();
		props.put("serverAddr", serverAddr);
		if (StringUtils.hasText(namespace)) {
			props.put("namespace", namespace);
		}
		if (StringUtils.hasText(username)) {
			props.put("username", username);
		}
		if (StringUtils.hasText(password)) {
			props.put("password", password);
		}

		try {
			ConfigService configService = NacosFactory.createConfigService(props);

			String group = resolve(environment, "spring.cloud.nacos.config.group");
			if (!StringUtils.hasText(group)) {
				group = "DEFAULT_GROUP";
			}

			for (String profile : profiles) {
				loadAndInject(environment, configService, group, "application-" + profile + "." + fileExtension, "nacos-fallback-shared-" + profile);
				if (StringUtils.hasText(appName)) {
					loadAndInject(environment, configService, group, appName + "-" + profile + "." + fileExtension, "nacos-fallback-ext-" + profile);
				}
			}
			if (StringUtils.hasText(appName)) {
				loadAndInject(environment, configService, group, appName + "." + fileExtension, "nacos-fallback-default");
			}
		} catch (NacosException e) {
			throw new RuntimeException("Failed to create Nacos ConfigService for fallback", e);
		}
	}

	private String resolve(ConfigurableEnvironment env, String key) {
		String value = env.getProperty(key);
		if (value == null) {
			return "";
		}
		try {
			return env.resolveRequiredPlaceholders(value);
		} catch (Exception e) {
			return value;
		}
	}

	private void loadAndInject(ConfigurableEnvironment environment, ConfigService configService,
			String group, String dataId, String propertySourceName) {
		try {
			String content = configService.getConfig(dataId, group, 5000);
			if (content == null || content.isEmpty()) {
				return;
			}

			PropertySource<?> existing = environment.getPropertySources().get(propertySourceName);
			if (existing != null) {
				return;
			}

			Map<String, Object> properties = new HashMap<>();
			org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
			Iterable<Object> docs = yaml.loadAll(content);
			for (Object doc : docs) {
				if (doc instanceof Map) {
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) doc;
					flatten("", map, properties);
				}
			}

			if (!properties.isEmpty()) {
				MapPropertySource ps = new MapPropertySource(propertySourceName, properties);
				environment.getPropertySources().addAfter("configurationProperties", ps);
			}
		} catch (NacosException e) {
		}
	}

	@SuppressWarnings("unchecked")
	private void flatten(String prefix, Map<String, Object> source, Map<String, Object> result) {
		for (Map.Entry<String, Object> entry : source.entrySet()) {
			String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Map) {
				flatten(key, (Map<String, Object>) value, result);
			} else if (value != null) {
				result.put(key, value);
			}
		}
	}
}
