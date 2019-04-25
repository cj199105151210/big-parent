package com.bosssoft.bigdata.admin.controller;

import javax.validation.Valid;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.admin.api.dto.UserDTO;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import com.bosssoft.bigdata.admin.service.SysUserService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import com.bosssoft.bigdata.common.security.annotation.Inner;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 *
 * @author lucky
 * @date 2018/12/16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理模块")
public class UserController {
	private final SysUserService userService;

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	@ApiOperation("查询当前登录用户信息")
	public R info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, "获取当前用户信息失败");
		}
		return new R<>(userService.findUserInfo(user));
	}
	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	@ApiOperation("根据用户名查询用户信息")
	@ApiImplicitParam(name = "username", value = "用户名称")
	public R info(@PathVariable String username) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
		}
		return new R<>(userService.findUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	@ApiOperation("根据用户id查询用户信息")
	@ApiImplicitParam(name = "id", value = "用户id")
	public R user(@PathVariable Integer id) {
		return new R<>(userService.selectUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	@ApiOperation("根据用户名查询用户信息")
	@ApiImplicitParam(name = "username", value = "用户名称")
	public R user(@PathVariable String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		return new R<>(userService.getOne(new QueryWrapper<>(condition)));
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	@ApiOperation(value = "删除用户", notes = "根据ID删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
	public R userDel(@PathVariable Integer id) {
		SysUser sysUser = userService.getById(id);
		return new R<>(userService.deleteUserById(sysUser));
	}

	@SysLog("批量删除用户信息")
	@DeleteMapping("/dels")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	@ApiOperation(value = "批量删除用户", notes = "根据ID集合(数组)删除用户")
	public R userDels(@RequestBody List<Integer> ids){
		return userService.deleteByIds(ids);
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	@ApiOperation("添加用户")
	public R user(@RequestBody UserDTO userDto) {
		return new R<>(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	@ApiOperation("更新用户信息")
	public R updateUser(@Valid @RequestBody UserDTO userDto) {
		return new R<>(userService.updateUser(userDto));
	}

	/**
	 * 重置用户密码
	 * @return R
	 */
	@SysLog("重置用户密码")
	@PutMapping("/reset-pwd/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	@ApiOperation("重置用户密码")
	public R resetPassword(@PathVariable("id") Integer id) {
		if (id == null){
			throw new IllegalArgumentException();
		}
		return new R<>(userService.resetPassword(id));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	@ApiOperation("分页查询用户")
	public R getUserPage(Page page, UserDTO userDTO) {
		return new R<>(userService.getUsersWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	@ApiOperation("更新用户信息")
	public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

	/**
	 * @param username 用户名称
	 * @return 上级部门用户列表
	 */
	@GetMapping("/ancestor/{username}")
	public R listAncestorUsers(@PathVariable String username) {
		return new R<>(userService.listAncestorUsers(username));
	}

	/**
	 * 用户注册
	 * @param sysUser
	 * @return
	 */
	@SysLog("注册用户(无角色)")
	@PostMapping("/register")
	@ApiOperation(value = "用户注册", notes = "{'username':'xxx', 'password':'123456'}")
	public R registerUser(@RequestBody SysUser sysUser, @RequestParam("rolecode") String rolecode) {
		if (StringUtil.isEmpty(sysUser.getUsername()) || StringUtil.isEmpty(sysUser.getPassword())){
			throw new IllegalArgumentException("用户名密码不能为空");
		}
		return new R<>(userService.saveUserEO(sysUser, rolecode));
	}

	/**
	 * 用户配角色
	 * @param username
	 * @param rolecode
	 * @return
	 */
	@SysLog("新用户赋角色")
	@PutMapping("/role")
	@ApiOperation(value = "用户配角色", notes = "username : 用户名, rolecode:角色标识")
	public R setUserRole(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "rolecode", required = false) String rolecode) {
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(rolecode)){
			throw new IllegalArgumentException("参数错误，不能为空");
		}
		return new R<>(userService.setUserRole(username, rolecode));
	}
}
