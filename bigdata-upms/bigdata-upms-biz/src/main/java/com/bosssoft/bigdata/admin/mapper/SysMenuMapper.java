package com.bosssoft.bigdata.admin.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.bigdata.admin.api.entity.SysMenu;
import com.bosssoft.bigdata.admin.api.vo.MenuVO;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 *
	 * @param roleId 角色ID
	 * @return
	 */
	List<MenuVO> listMenusByRoleId(Integer roleId);

	/**
	 * 通过角色ID查询权限
	 *
	 * @param roleIds Ids
	 * @return
	 */
	List<String> listPermissionsByRoleIds(String roleIds);
}
