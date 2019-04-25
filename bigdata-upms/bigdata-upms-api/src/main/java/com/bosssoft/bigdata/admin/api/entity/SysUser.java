package com.bosssoft.bigdata.admin.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
@Data
@ApiModel(description = "系统用户实体")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	@ApiModelProperty(value = "用户id")
	private Integer userId;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "用户密码")
	private String password;
	/**
	 * 随机盐
	 */
	@JsonIgnore
	@ApiModelProperty(value = "随机盐")
	private String salt;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	/**
	 * 0-正常，1-删除
	 */
	@ApiModelProperty(value = "逻辑删除标识【0-正常，1-删除】")
	@TableLogic
	private String delFlag;

	/**
	 * 锁定标记
	 */
	@ApiModelProperty(value = "是否失效")
	private String lockFlag;

	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String avatar;

	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID")
	private Integer deptId;

	/**
	 * 租户ID
	 */
	@ApiModelProperty(value = "租户ID")
	private Integer tenantId;

	/**
	 * 微信openid
	 */
	@ApiModelProperty("微信openid")
	private String wxOpenid;

	/**
	 * QQ openid
	 */
	@ApiModelProperty("QQ号")
	private String qqOpenid;

}
