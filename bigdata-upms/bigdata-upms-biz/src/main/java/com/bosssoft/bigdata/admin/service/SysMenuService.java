package com.bosssoft.bigdata.admin.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.bigdata.admin.api.entity.SysMenu;
import com.bosssoft.bigdata.admin.api.vo.MenuVO;
import com.bosssoft.bigdata.common.core.util.R;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
public interface SysMenuService extends IService<SysMenu> {
	/**
	 * 通过角色编号查询URL 权限
	 *
	 * @param roleId 角色ID
	 * @return 菜单列表
	 */
	List<MenuVO> findMenuByRoleId(Integer roleId);

	/**
	 * 级联删除菜单
	 *
	 * @param id 菜单ID
	 * @return 成功、失败
	 */
	R removeMenuById(Integer id);

	/**
	 * 更新菜单信息
	 *
	 * @param sysMenu 菜单信息
	 * @return 成功、失败
	 */
	Boolean updateMenuById(SysMenu sysMenu);

	/**
	 * 通过菜单ID 查询其所有子菜单
	 *
	 * @param menuId
	 * @return
	 */
	List<SysMenu> findAllMenuByMenuId(Integer menuId);
}
