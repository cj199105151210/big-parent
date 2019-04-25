package com.bosssoft.bigdata.admin.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_id", type = IdType.AUTO)
	private Integer roleId;

	@NotBlank(message = "角色名称 不能为空")
	private String roleName;

	@NotBlank(message = "角色标识 不能为空")
	private String roleCode;

	@NotBlank(message = "角色描述 不能为空")
	private String roleDesc;

	@NotNull(message = "数据权限类型 不能为空")
	private Integer dsType;

	private String dsScope;

	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	private String delFlag;

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

}
