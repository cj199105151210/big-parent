package com.bosssoft.bigdata.admin.api.feign;

import java.util.List;

import com.bosssoft.bigdata.admin.api.dto.MenuTree;
import com.bosssoft.bigdata.admin.api.dto.UserInfo;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import com.bosssoft.bigdata.common.core.constant.SecurityConstants;
import com.bosssoft.bigdata.common.core.constant.ServiceNameConstants;
import com.bosssoft.bigdata.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lucky
 * @date 2019/2/22
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfo> info(@PathVariable("username") String username
			, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号或手机号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @param from  调用标志
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfo> social(@PathVariable("inStr") String inStr
			, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@GetMapping("/user/ancestor/{username}")
	R<List<SysUser>> ancestorUsers(@PathVariable("username") String username);

	/**
	 * 查询当前管理员菜单信息
	 * @author GYZ
	 * @date 2019-03-06
	 * @return R
	 */
	@GetMapping("/menu/")
	R<List<MenuTree>> getUserMenu();

	/**
	 * 用户注册
	 * @param sysUser
	 * @return
	 */
	@PostMapping("/user/register")
	R<SysUser> registerUser(@RequestBody SysUser sysUser, @RequestParam("rolecode") String rolecode);

	/**
	 * 用户配角色
	 * @param username
	 * @param rolecode
	 * @return
	 */
	@PutMapping("/user/role")
	R setUserRole(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "rolecode", required = false) String rolecode);
}
