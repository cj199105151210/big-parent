package com.bosssoft.bigdata.usercenter.api.annotation;

import java.lang.annotation.*;

/**
 * @author fanghuaizheng
 * @Description:排序使用
 * @date 2019-03-19 16:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrderType {
    String value() default "ASC";
}
