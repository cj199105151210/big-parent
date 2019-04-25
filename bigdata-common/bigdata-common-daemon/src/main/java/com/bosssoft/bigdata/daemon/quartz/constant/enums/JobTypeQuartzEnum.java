package com.bosssoft.bigdata.daemon.quartz.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 * <p>
 * 任务类型枚举
 */
@Getter
@AllArgsConstructor
public enum JobTypeQuartzEnum {
	/**
	 * 反射java类
	 */
	JAVA("1", "反射java类"),

	/**
	 * spring bean 的方式
	 */
	SPRING_BEAN("2", "spring bean容器实例"),

	/**
	 * rest 调用
	 */
	REST("3", "rest调用"),

	/**
	 * jar
	 */
	JAR("4", "jar调用");

	/**
	 * 类型
	 */
	private final String type;
	/**
	 * 描述
	 */
	private final String description;
}
