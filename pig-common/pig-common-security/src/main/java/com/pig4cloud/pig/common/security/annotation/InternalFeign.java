package com.pig4cloud.pig.common.security.annotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InternalFeign {
}
