package com.bosssoft.bigdata.admin.api.dto;

import java.util.List;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lucky
 * @date 2019/2/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {
	/**
	 * 角色ID
	 */
	private List<Integer> role;

	private Integer deptId;

	/**
	 * 新密码
	 */
	private String newpassword1;
}
