package com.pig4cloud.pig.common.data.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /** 主表别名，如 "loan_order o"，填 "o" */
    String tableAlias() default "";

    /** 业务表名，用于匹配 sys_role_data_scope.business_table */
    String tableName();

    /** 归属人字段名，优先级高于数据库配置 */
    String ownerColumn() default "";
}