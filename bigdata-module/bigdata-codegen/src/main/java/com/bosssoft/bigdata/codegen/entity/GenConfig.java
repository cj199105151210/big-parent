package com.bosssoft.bigdata.codegen.entity;

import lombok.Data;

/**
 * @author lucky
 * @date 2019-03-31
 * <p>
 * 生成配置
 */
@Data
public class GenConfig {
	/**
	 * 数据源ID
	 */
	private Integer id;
	/**
	 * 包名
	 */
	private String packageName;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 表前缀
	 */
	private String tablePrefix;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 表备注
	 */
	private String comments;
}
