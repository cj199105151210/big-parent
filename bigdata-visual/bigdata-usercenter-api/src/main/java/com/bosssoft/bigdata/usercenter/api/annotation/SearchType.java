package com.bosssoft.bigdata.usercenter.api.annotation;

import java.lang.annotation.*;

/**
 * 模糊匹配使用
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchType {

    String value() default "";
}
