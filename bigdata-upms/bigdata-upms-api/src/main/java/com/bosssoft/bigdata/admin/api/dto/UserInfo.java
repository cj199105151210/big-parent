package com.bosssoft.bigdata.admin.api.dto;

import java.io.Serializable;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import lombok.Data;

/**
 * @author lucky
 * @date 2019/2/18
 * <p>
 * commit('SET_ROLES', data)
 * commit('SET_NAME', data)
 * commit('SET_AVATAR', data)
 * commit('SET_INTRODUCTION', data)
 * commit('SET_PERMISSIONS', data)
 */
@Data
public class UserInfo implements Serializable {
	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Integer[] roles;

	/**
	 * 是否是使用初始密码登录
	 */
	private Boolean isInitial = Boolean.FALSE;
}
