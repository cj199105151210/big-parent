package com.bosssoft.bigdata.common.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-27 18:16
 */
@Retention(RUNTIME)
@Target({FIELD})
@Inherited
@Documented
public @interface DictParam {
    /**
     * 需要翻译后字段名
     * @return
     */
    String nameFiled() default "";

    /**
     * 字典类型
     * @return
     */
    String dictType() default "";
}
