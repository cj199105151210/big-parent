package com.bosssoft.bigdata.admin.service.impl;

import java.util.List;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.admin.api.entity.SysMenu;
import com.bosssoft.bigdata.admin.api.entity.SysRoleMenu;
import com.bosssoft.bigdata.admin.api.vo.MenuVO;
import com.bosssoft.bigdata.admin.mapper.SysMenuMapper;
import com.bosssoft.bigdata.admin.mapper.SysRoleMenuMapper;
import com.bosssoft.bigdata.admin.service.SysMenuService;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
	private final SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Cacheable(value = "menu_details", key = "#roleId  + '_menu'")
	public List<MenuVO> findMenuByRoleId(Integer roleId) {
		return baseMapper.listMenusByRoleId(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = "menu_details", allEntries = true)
	public R removeMenuById(Integer id) {
		// 查询父节点为当前节点的节点
		List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query()
				.lambda().eq(SysMenu::getParentId, id));
		if (CollUtil.isNotEmpty(menuList)) {
			return R.builder()
					.code(CommonConstants.FAIL)
					.msg("菜单含有下级不能删除").build();
		}

		sysRoleMenuMapper
				.delete(Wrappers.<SysRoleMenu>query()
						.lambda().eq(SysRoleMenu::getMenuId, id));

		//删除当前菜单及其子菜单
		return new R(this.removeById(id));
	}

	@Override
	@CacheEvict(value = "menu_details", allEntries = true)
	public Boolean updateMenuById(SysMenu sysMenu) {
		return this.updateById(sysMenu);
	}

	@Override
	//@Cacheable(value = "menu_details", key = "#menuId  + '_menu'")
	public List<SysMenu> findAllMenuByMenuId(Integer menuId) {
		return sysRoleMenuMapper.findAllMenuByMenuId(menuId);
	}
}
