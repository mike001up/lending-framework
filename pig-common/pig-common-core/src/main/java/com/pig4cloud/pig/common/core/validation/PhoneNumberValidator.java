package com.pig4cloud.pig.common.core.validation;

import com.google.i18n.phonenumbers.NumberParseException;

// 文件位置: pigx-common-core/src/main/java/com/pig4cloud/pigx/common/core/validation/PhoneValidator.java

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhone, String> {

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private String defaultRegion;

    @Override
    public void initialize(ValidPhone constraintAnnotation) {
        // 从注解中获取配置的默认国家码
        this.defaultRegion = constraintAnnotation.defaultRegion();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        // 空值校验交给 @NotBlank 等注解处理，这里直接通过
        if (StringUtils.isBlank(phoneNumber)) {
            return true;
        }
        try {
            // 根据注解中传入的默认区域进行解析
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phoneNumber, "ZZ");
            // 使用 isValidNumber 进行全面校验，确保号码真实有效
            return phoneNumberUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            // 解析失败，直接返回无效
            return false;
        }
    }
}