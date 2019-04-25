package com.bosssoft.bigdata.admin.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.admin.api.dto.UserDTO;
import com.bosssoft.bigdata.admin.api.dto.UserInfo;
import com.bosssoft.bigdata.admin.api.entity.*;
import com.bosssoft.bigdata.admin.api.vo.MenuVO;
import com.bosssoft.bigdata.admin.api.vo.UserVO;
import com.bosssoft.bigdata.admin.mapper.SysUserMapper;
import com.bosssoft.bigdata.admin.service.*;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.core.utils.CollectionUtil;
import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.common.data.datascope.DataScope;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lucky
 * @date 2017/10/31
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	private final SysMenuService sysMenuService;
	private final SysRoleService sysRoleService;
	private final SysDeptService sysDeptService;
	private final SysUserRoleService sysUserRoleService;
	private final SysDeptRelationService sysDeptRelationService;

	/**
	 * 保存用户信息
	 *
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		baseMapper.insert(sysUser);
		List<SysUserRole> userRoleList = userDto.getRole()
				.stream().map(roleId -> {
					SysUserRole userRole = new SysUserRole();
					userRole.setUserId(sysUser.getUserId());
					userRole.setRoleId(roleId);
					return userRole;
				}).collect(Collectors.toList());
		return sysUserRoleService.saveBatch(userRoleList);
	}

	/**
	 * 通过查用户的全部信息
	 *
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserInfo findUserInfo(SysUser sysUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setSysUser(sysUser);
		//设置角色列表  （ID）
		List<Integer> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId())
				.stream()
				.map(SysRole::getRoleId)
				.collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleIds, Integer.class));

		//设置权限列表（menu.permission）
		Set<String> permissions = new HashSet<>();
		roleIds.forEach(roleId -> {
			List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
					.stream()
					.filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
					.map(MenuVO::getPermission)
					.collect(Collectors.toList());
			permissions.addAll(permissionList);
		});
		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
		userInfo.setIsInitial(ENCODER.matches(CommonConstants.INITIAL_PASSWORD, sysUser.getPassword()));
		return userInfo;
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	@Override
	public IPage getUsersWithRolePage(Page page, UserDTO userDTO) {
		return baseMapper.getUserVosPage(page, userDTO, new DataScope());
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserVO selectUserVoById(Integer id) {
		return baseMapper.getUserVoById(id);
	}

	/**
	 * 删除用户
	 *
	 * @param sysUser 用户
	 * @return Boolean
	 */
	@Override
	@CacheEvict(value = "user_details", key = "#sysUser.username")
	public Boolean deleteUserById(SysUser sysUser) {
		sysUserRoleService.deleteByUserId(sysUser.getUserId());
		this.removeById(sysUser.getUserId());
		return Boolean.TRUE;
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public R<Boolean> deleteByIds(List<Integer> ids) {
		if (CollectionUtil.isEmpty(ids)){
			throw new IllegalArgumentException("参数错误");
		}
		sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("user_id", ids));
		return new R<>(this.removeByIds(ids));
	}

	@Override
	@CacheEvict(value = "user_details", key = "#userDto.username")
	public R<Boolean> updateUserInfo(UserDTO userDto) {
		UserVO userVO = baseMapper.getUserVoByUsername(userDto.getUsername());
		SysUser sysUser = new SysUser();
		if (StrUtil.isNotBlank(userDto.getPassword())
				&& StrUtil.isNotBlank(userDto.getNewpassword1())) {
			if (ENCODER.matches(userDto.getPassword(), userVO.getPassword())) {
				sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
			} else {
				log.warn("原密码错误，修改密码失败:{}", userDto.getUsername());
				return new R<>(Boolean.FALSE, "原密码错误，修改失败");
			}
		}
		sysUser.setPhone(userDto.getPhone());
		sysUser.setUserId(userVO.getUserId());
		sysUser.setAvatar(userDto.getAvatar());
		return new R<>(this.updateById(sysUser));
	}

	@Override
	@CacheEvict(value = "user_details", key = "#userDto.username")
	public Boolean updateUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(userDto.getPassword())) {
			sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		}
		this.updateById(sysUser);

		sysUserRoleService.remove(Wrappers.<SysUserRole>update().lambda()
				.eq(SysUserRole::getUserId, userDto.getUserId()));
		userDto.getRole().forEach(roleId -> {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			userRole.setRoleId(roleId);
			userRole.insert();
		});
		return Boolean.TRUE;
	}

	/**
	 * 重置用户密码
	 * @param id
	 * @return
	 */
	@Override
	public Boolean resetPassword(Integer id) {
		SysUser sysUser =  this.getById(id);
		if (sysUser == null){
			throw new IllegalArgumentException("can not find this user!");
		}
		sysUser.setPassword(ENCODER.encode(CommonConstants.INITIAL_PASSWORD));
		return this.updateById(sysUser);
	}

	/**
	 * 查询上级部门的用户信息
	 *
	 * @param username 用户名
	 * @return R
	 */
	@Override
	public List<SysUser> listAncestorUsers(String username) {
		SysUser sysUser = this.getOne(Wrappers.<SysUser>query().lambda()
				.eq(SysUser::getUsername, username));

		SysDept sysDept = sysDeptService.getById(sysUser.getDeptId());
		if (sysDept == null) {
			return null;
		}

		Integer parentId = sysDept.getParentId();
		return this.list(Wrappers.<SysUser>query().lambda()
				.eq(SysUser::getDeptId, parentId));
	}

	/**
	 * 获取当前用户的子部门信息
	 *
	 * @return 子部门列表
	 */
	private List<Integer> getChildDepts() {
		Integer deptId = SecurityUtils.getUser().getDeptId();
		//获取当前部门的子部门
		return sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor, deptId))
				.stream()
				.map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SysUser saveUserEO(SysUser sysUser, String roleCode) {
		SysUser dbUser = this.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, sysUser.getUsername()));
		if (dbUser != null){
			throw new IllegalArgumentException("用户已存在" + sysUser.getUsername());
		}
		String pwd = sysUser.getPassword();
		sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(pwd));
		baseMapper.insert(sysUser);

		if(StringUtil.isNotBlank(roleCode)){
			SysRole sysRole = sysRoleService.getOne(Wrappers.<SysRole>query().lambda().eq(SysRole::getRoleCode, roleCode));
			if (sysRole == null){
				throw new IllegalArgumentException("参数错误：找不到角色" + roleCode);
			}
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			userRole.setRoleId(sysRole.getRoleId());
			userRole.insert();
		}

		return sysUser;
	}

	@Override
	public Boolean setUserRole(String username, String roleCode) {
		SysUser sysUser = this.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
		if (sysUser == null){
			throw new IllegalArgumentException("找不到用户" + username);
		}
		SysRole sysRole = sysRoleService.getOne(Wrappers.<SysRole>query().lambda().eq(SysRole::getRoleCode, roleCode));
		if (sysRole == null){
			throw new IllegalArgumentException("找不到角色" + roleCode);
		}
		SysUserRole sysUserRole = sysUserRoleService.getOne(Wrappers.<SysUserRole>query().lambda()
				.eq(SysUserRole::getUserId, sysUser.getUserId())
				.eq(SysUserRole::getRoleId, sysRole.getRoleId()));
		if (sysUserRole == null){
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			userRole.setRoleId(sysRole.getRoleId());
			return userRole.insert();
		} else {
			return null;
		}
	}
}
