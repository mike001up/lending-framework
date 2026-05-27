package com.pig4cloud.pig.common.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义异常：用户被注销或禁止登录
 */
public class UserBlockedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserBlockedException(String msg) {
        super(msg);
    }

    public UserBlockedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
