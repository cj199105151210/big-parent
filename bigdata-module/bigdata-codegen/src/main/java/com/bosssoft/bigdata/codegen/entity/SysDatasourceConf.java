package com.bosssoft.bigdata.codegen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 数据源表
 * <p>
 * @author lucky
 * @date 2019-03-31
 */
@Data
@TableName("sys_datasource_conf")
@EqualsAndHashCode(callSuper = true)
public class SysDatasourceConf extends Model<SysDatasourceConf> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 *
	 */
	private String url;
	/**
	 *
	 */
	private String username;
	/**
	 *
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private LocalDateTime createDate;
	/**
	 * 更新
	 */
	private LocalDateTime updateDate;
	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;
	/**
	 * 租户ID
	 */
	private Integer tenantId;

}
