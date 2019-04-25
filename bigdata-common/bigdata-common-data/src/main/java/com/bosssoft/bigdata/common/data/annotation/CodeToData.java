package com.bosssoft.bigdata.common.data.annotation;

import java.lang.annotation.*;

/**
 * @author fanghuaizheng
 * @Description:字典项key转化为value 注解
 * @date 2019-04-10 11:20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CodeToData {
    String value() default "";
}
