package com.pig4cloud.pig.admin.mq;

import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.mq.event.LoginFailureMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginFailureService {

    private final StringRedisTemplate redisTemplate;
    private final SysUserService sysUserService;

    public void handleLoginFailure(LoginFailureMessage message) {
        String username = message.getPayload().getUsername();
        if (username == null) {
            log.warn("登录失败消息缺少用户名，忽略");
            return;
        }

        String key = String.format(SecurityConstants.REDIS_KEY_AUTH_LOGIN_FAILURE_KEY, username);

        // Redis 原子自增
        Long count = redisTemplate.opsForValue().increment(key);
        if (count == 1) {
            // 首次失败，设置 10 分钟过期
            redisTemplate.expire(key, SecurityConstants.AUTH_LOGIN_WINDOW_MINUTES, TimeUnit.MINUTES);
        }

        log.debug("用户 {} 登录失败，当前计数: {}", username, count);

        // 达到阈值则锁定
        if (count != null && count >= SecurityConstants.AUTH_LOGIN_MAX_FAILURES) {
            sysUserService.lockUser(username);
            redisTemplate.delete(key);
            log.info("用户 {} 因登录失败达到 {} 次，已被锁定 {} 分钟",
                    username,
                    SecurityConstants.AUTH_LOGIN_MAX_FAILURES,
                    SecurityConstants.AUTH_LOGIN_LOCK_MINUTES);
        }
    }
}
