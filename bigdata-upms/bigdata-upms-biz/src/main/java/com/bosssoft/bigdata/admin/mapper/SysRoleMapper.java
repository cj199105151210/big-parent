package com.bosssoft.bigdata.admin.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.bigdata.admin.api.entity.SysRole;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> listRolesByUserId(Integer userId);
}
