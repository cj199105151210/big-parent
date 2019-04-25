package com.bosssoft.bigdata.common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lucky
 * @date 2019/2/23
 * <p>
 * 服务调用不鉴权注解
 * 参考https://blog.csdn.net/liang100k/article/details/79515910
 * 注解@Target用法,METHOD-可用于方法上;TYPE-可用于类或者接口上
 * 注解@Retention可以用来修饰注解，是注解的注解，称为元注解
 * 注解@Documented注解表明这个注释是由 javadoc记录的
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

	/**
	 * 是否AOP统一处理
	 *
	 * @return false, true
	 */
	boolean value() default true;

	/**
	 * 需要特殊判空的字段(预留)
	 *
	 * @return {}
	 */
	String[] field() default {};
}
