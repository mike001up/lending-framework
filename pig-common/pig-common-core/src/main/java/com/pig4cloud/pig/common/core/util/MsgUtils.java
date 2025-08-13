package com.pig4cloud.pig.common.core.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


/**
 * i18n 工具类
 *
 * @author lengleng
 * @date 2022/3/30
 */
@UtilityClass
public class MsgUtils {

	/**
	 * 通过code 获取中文错误信息
	 * @param code
	 * @return
	 */
	public String getMessage(String code) {
		MessageSource messageSource = SpringContextHolder.getBean("messageSource");
		return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}

	/**
	 * 通过code 和参数获取中文错误信息
	 * @param code
	 * @return
	 */
	public String getMessage(String code, Object... objects) {
		MessageSource messageSource = SpringContextHolder.getBean("messageSource");
		return messageSource.getMessage(code, objects, LocaleContextHolder.getLocale());
	}

	/**
	 * security 通过code 和参数获取中文错误信息
	 * @param code
	 * @return
	 */
	public String getSecurityMessage(String code, Object... objects) {
		MessageSource messageSource = SpringContextHolder.getBean("securityMessageSource");
		return messageSource.getMessage(code, objects, LocaleContextHolder.getLocale());
	}

}
