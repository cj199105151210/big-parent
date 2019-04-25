package com.bosssoft.bigdata.common.core.utils;

import org.springframework.lang.Nullable;

/**
 * @author: Lucky
 * @date: 2019/03/26
 * <p>对象工具类
 */
public class ObjectUtil extends org.springframework.util.ObjectUtils {

	/**
	 * 判断元素不为空
	 * @param obj object
	 * @return boolean
	 */
	public static boolean isNotEmpty(@Nullable Object obj) {
		return !ObjectUtil.isEmpty(obj);
	}

}

