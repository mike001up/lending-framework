package com.pig4cloud.pig.common.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义异常：用户不存在
 */
public class UserNotExistException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotExistException(String msg) {
        super(msg);
    }

    public UserNotExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
