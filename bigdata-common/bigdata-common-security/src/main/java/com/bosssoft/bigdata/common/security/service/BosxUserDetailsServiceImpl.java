package com.bosssoft.bigdata.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.bosssoft.bigdata.admin.api.dto.UserInfo;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import com.bosssoft.bigdata.admin.api.feign.RemoteUserService;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.constant.SecurityConstants;
import com.bosssoft.bigdata.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 *
 * @author lucky
 */
@Slf4j
@Service
@AllArgsConstructor
public class BosxUserDetailsServiceImpl implements BosxUserDetailsService {
	private final RemoteUserService remoteUserService;
	private final CacheManager cacheManager;

	//@Autowired
	//private com.bosssoft.bigdata.usercenter.api.feign.RemoteUserService uniRemoteUserService;


	//@Autowired
	//RedisTemplate redisTemplate;


	/**
	 * 用户密码登录
	 *
	 * @param username 用户名
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		Cache cache = cacheManager.getCache(SecurityConstants.CACHE_EVICT_SYS_USER);
		if (cache != null && cache.get(username) != null) {
			return (BosxUser) cache.get(username).get();
			//return (UserDetails) cache.get(username).get();
		}

		R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		cache.put(username, userDetails);
		return userDetails;
	}


	/**
	 * 根据社交登录code 登录
	 *
	 * @param inStr TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserBySocial(String inStr) {
		return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
		/*

		log.info("\n ========= 通过 uniRemoteUserService 查询用户 {} 信息 ========= \n", inStr);

		R<UserMessage> userInfo = uniRemoteUserService.getUserInfo(inStr);
		UserMessage userMessage = userInfo.getData();

		if (userMessage==null){
		    throw new UsernameNotFoundException(LoginErrorEnum.NO_USER.getDesc());
		}

		log.info(
			"\n ========= 查询成功, password {} - userNo {} - idCard {} ========= \n",
			userMessage.getPassword(),
			userMessage.getUserNo(),
			userMessage.getIdCard()
		);
		//设置用户被锁定
		int error_num = 0;
		if (redisTemplate.opsForValue().get(CommonConstants.BOSX_PASSWORD_PREFIX+inStr)!=null){
			error_num = (int) redisTemplate.opsForValue().get(CommonConstants.BOSX_PASSWORD_PREFIX+inStr);
		}

		if (CommonConstants.ERROR_COUNT.intValue()<=error_num){
			throw new UsernameNotFoundException(LoginErrorEnum.USER_LOCKED.getDesc());
		}

		return new User(
			inStr,
			userMessage.getPassword(),
			true,
			true,
			true,
			true,
			Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
		);*/
	}

	/**
	 * 构建userdetails
	 *
	 * @param result 用户信息
	 * @return
	 */
	private UserDetails getUserDetails(R<UserInfo> result) {
		if (result == null || result.getData() == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		UserInfo info = result.getData();
		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

		}
		Collection<? extends GrantedAuthority> authorities
				= AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();
		boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
		// 构造security用户

		return new BosxUser(user.getUserId(), user.getDeptId(), user.getTenantId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(), enabled,
				true, true, !CommonConstants.STATUS_LOCK.equals(user.getLockFlag()), authorities);
	}
}
