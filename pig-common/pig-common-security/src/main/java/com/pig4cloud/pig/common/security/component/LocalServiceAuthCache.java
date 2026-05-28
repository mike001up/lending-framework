package com.pig4cloud.pig.common.security.component;

import cn.hutool.core.util.StrUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.security.service.RemoteServiceAuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class LocalServiceAuthCache {

	private final ObjectProvider<RemoteServiceAuthClient> remoteAuthClientProvider;

	@Value("${pigx.service-auth.rule-cache-ttl:30000}")
	private long cacheTtlMs;

	private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();

	public LocalServiceAuthCache(ObjectProvider<RemoteServiceAuthClient> remoteAuthClientProvider) {
		this.remoteAuthClientProvider = remoteAuthClientProvider;
	}

	public boolean checkPermission(String callerServiceId, String providerServiceId, String requestPath) {
		String cacheKey = callerServiceId + ":" + providerServiceId + ":" + requestPath;
		CacheEntry entry = cache.get(cacheKey);
		if (entry != null && !entry.isExpired()) {
			return entry.isAllowed();
		}

		try {
			RemoteServiceAuthClient client = remoteAuthClientProvider.getIfAvailable();
			if (client == null) {
				log.warn("RemoteServiceAuthClient未可用, 跳过远程授权校验");
				return true;
			}
			Boolean allowed = client.checkServiceAuth(SecurityConstants.FROM_IN,
					callerServiceId, providerServiceId, requestPath);
			boolean result = Boolean.TRUE.equals(allowed);
			cache.put(cacheKey, new CacheEntry(result, System.currentTimeMillis() + cacheTtlMs));
			return result;
		}
		catch (Exception ex) {
			log.error("远程校验服务授权失败, caller: {}, provider: {}, path: {}",
					callerServiceId, providerServiceId, requestPath, ex);
			return false;
		}
	}

	public void evictAll() {
		cache.clear();
	}

	private static class CacheEntry {

		private final boolean allowed;

		private final long expiresAt;

		CacheEntry(boolean allowed, long expiresAt) {
			this.allowed = allowed;
			this.expiresAt = expiresAt;
		}

		boolean isAllowed() {
			return allowed;
		}

		boolean isExpired() {
			return System.currentTimeMillis() > expiresAt;
		}

	}

}
