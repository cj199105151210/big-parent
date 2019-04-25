package com.bosssoft.bigdata.codegen.util;

import lombok.experimental.UtilityClass;

/**
 * @author lucky
 * @date 2019-03-31
 * <p>
 * 保存上线文中数据库路由
 */
@UtilityClass
public class DynamicDataSourceContextHolder {
	private final ThreadLocal<Integer> CONTEXT_HOLDER = new ThreadLocal<>();

	public void setDataSourceType(Integer dataSourceType) {
		CONTEXT_HOLDER.set(dataSourceType);
	}

	public Integer getDataSourceType() {
		return CONTEXT_HOLDER.get();
	}

	public void clearDataSourceType() {
		CONTEXT_HOLDER.remove();
	}
}