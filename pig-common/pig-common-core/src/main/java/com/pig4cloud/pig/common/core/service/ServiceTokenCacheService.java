package com.pig4cloud.pig.common.core.service;

/**
 * 服务令牌缓存服务（由安全模块实现）
 */
public interface ServiceTokenCacheService {
    /**
     * 获取当前服务的内部令牌
     * @return 令牌字符串
     */
    // String getToken();
    
    /**
     * 获取或刷新服务令牌（自动处理缓存）
     * @return 令牌字符串
     */
    String getOrRefreshToken();
}
