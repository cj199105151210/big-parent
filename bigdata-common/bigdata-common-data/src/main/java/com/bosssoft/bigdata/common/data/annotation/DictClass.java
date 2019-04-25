package com.bosssoft.bigdata.common.data.annotation;

import java.lang.annotation.*;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-28 14:32
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DictClass {
    //字典值的类型
//    String value() default "";
}
