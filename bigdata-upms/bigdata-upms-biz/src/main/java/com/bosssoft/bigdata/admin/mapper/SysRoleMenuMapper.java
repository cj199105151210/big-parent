package com.bosssoft.bigdata.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.bigdata.admin.api.entity.SysMenu;
import com.bosssoft.bigdata.admin.api.entity.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 通过菜单ID 查询其所有子菜单
     * @param menuId
     * @return
     */
    List<SysMenu> findAllMenuByMenuId(Integer menuId);
}
