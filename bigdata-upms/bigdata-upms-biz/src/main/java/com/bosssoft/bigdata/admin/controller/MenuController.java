package com.bosssoft.bigdata.admin.controller;

import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bosssoft.bigdata.admin.api.dto.MenuTree;
import com.bosssoft.bigdata.admin.api.entity.SysMenu;
import com.bosssoft.bigdata.admin.api.vo.MenuVO;
import com.bosssoft.bigdata.admin.api.vo.TreeUtil;
import com.bosssoft.bigdata.admin.service.SysMenuService;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.common.security.service.BosxUser;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单管理
 *
 * @author lucky
 * @date 2019-03-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
public class MenuController {
    private final SysMenuService sysMenuService;

    /**
     * 返回当前用户的树形菜单集合
     *
     * @return 当前用户的树形菜单
     */
    @GetMapping
    public R getUserMenu() {
        // 获取符合条件的菜单
        Set<MenuVO> all = new HashSet<>();
        SecurityUtils.getRoles()
                .forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
        List<MenuTree> menuTreeList = all.stream()
                .filter(menuVo -> CommonConstants.MENU.equals(menuVo.getType()))
                .map(MenuTree::new)
                .sorted(Comparator.comparingInt(MenuTree::getSort))
                .collect(Collectors.toList());
        return new R<>(TreeUtil.build(menuTreeList, CommonConstants.TREE_ROOT_ID));
    }

    /**
     * 返回树形菜单集合
     *
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public R getTree() {
        return new R<>(TreeUtil.buildTree(sysMenuService.list(Wrappers.emptyWrapper()), CommonConstants.TREE_ROOT_ID));
    }

    /**
     * 返回角色的菜单集合
     *
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/{roleId}")
    public List getRoleTree(@PathVariable Integer roleId) {
        return sysMenuService.findMenuByRoleId(roleId)
                .stream()
                .map(MenuVO::getMenuId)
                .collect(Collectors.toList());
    }

    /**
     * 通过ID查询菜单的详细信息
     *
     * @param id 菜单ID
     * @return 菜单详细信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        return new R<>(sysMenuService.getById(id));
    }

    /**
     * 新增菜单
     *
     * @param sysMenu 菜单信息
     * @return success/false
     */
    @SysLog("新增菜单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_add')")
    public R save(@Valid @RequestBody SysMenu sysMenu) {
        R result = null;
        try {
            result = new R<>(sysMenuService.save(sysMenu));
        } catch (Exception e) {
            result.setMsg("保存失败，请稍后重试");
            result.setCode(CommonConstants.FAIL);
        }
        return result;
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return success/false
     */
    @SysLog("删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_menu_del')")
    public R removeById(@PathVariable Integer id) {
        return sysMenuService.removeMenuById(id);
    }

    /**
     * 更新菜单
     *
     * @param sysMenu
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_menu_edit')")
    public R update(@Valid @RequestBody SysMenu sysMenu) {
        return new R<>(sysMenuService.updateMenuById(sysMenu));
    }

    /**
     * 返回菜单的集合
     * 通过菜单ID 查询其所有子菜单
     * @param menuPre
     * @param menuId
     * @return
     */
    @ApiOperation(value="通过菜单父子ID同时查询其所有子菜单",tags={"获取查询其所有子菜单"},notes="注意菜单父子集关系")
    @GetMapping("/tree/menu/{menuPre}/{menuId}")
    public R getMenuTree(@ApiParam(name="menuPre",value="父菜单ID",required=true) @PathVariable Integer menuPre,
                         @ApiParam(name="menuId",value="子菜单ID",required=true) @PathVariable Integer menuId) {
        return new R<>(TreeUtil.buildTree(sysMenuService.findAllMenuByMenuId(menuId), menuPre));
    }

    /**
     * 返回菜单的集合
     * 通过菜单ID 查询其所有子菜单
     * @param menuId
     * @return
     */
    @ApiOperation(value="通过菜单ID查询其所有子菜单",tags={"获取查询其所有子菜单"},notes="注意是菜单ID")
    @GetMapping("/tree/menu/{menuId}")
    public List<SysMenu> getTreeMenuByUserId(@ApiParam(name="menuId",value="菜单ID",required=true) @PathVariable Integer menuId) {
        return sysMenuService.findAllMenuByMenuId(menuId);
    }

    /**
     * 后端获取用户信息<br/>
     * 登陆完成后进入主页面，然后点击具体的菜单，这时候就会跟相关的微服务产生交互，在后端怎么获取到用户信息
     * 步骤：
     * 1.获取token 使用request.getHeader(HttpHeaders.AUTHORIZATION)
     * 2.使用token访问用户接口http://bigdata-gateway:2999/admin/user/info 带上 authorization: Bearer TOKEN信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value="TODO临时后端获取用户信息",tags={"TODO临时后端获取用户信息"},notes="注意获取Bearer")
    @GetMapping("/tree/menu/backend/togetuserinfo")
    public String backendToGetUserInfo(HttpServletRequest request) {
        //方式1：方法获取用户信息
        BosxUser bosxUser = SecurityUtils.getUser();
        //方式2：接口获取用户token
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        //host 配置 172.**.***.8 bigdata-gateway
        String url = "http://bigdata-gateway:2999/admin/user/info";
        String result = HttpRequest.get(url)
                .header("accept", "*/*")
                .header("authorization", "Bearer " + bearer)
                .execute().body();
        return result;
    }
}
