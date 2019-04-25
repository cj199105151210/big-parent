package com.bosssoft.bigdata.admin.service.impl;

import java.util.Map;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.admin.api.dto.UserInfo;
import com.bosssoft.bigdata.admin.api.entity.SysSocialDetails;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import com.bosssoft.bigdata.admin.handler.LoginHandler;
import com.bosssoft.bigdata.admin.mapper.SysSocialDetailsMapper;
import com.bosssoft.bigdata.admin.mapper.SysUserMapper;
import com.bosssoft.bigdata.admin.service.SysSocialDetailsService;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author lucky
 * @date 2018年08月16日
 */
@Slf4j
@AllArgsConstructor
@Service("sysSocialDetailsService")
public class SysSocialDetailsServiceImpl extends ServiceImpl<SysSocialDetailsMapper, SysSocialDetails> implements SysSocialDetailsService {
	private final Map<String, LoginHandler> loginHandlerMap;
	private final CacheManager cacheManager;
	private final SysUserMapper sysUserMapper;

	/**
	 * 绑定社交账号
	 *
	 * @param type type
	 * @param code code
	 * @return
	 */
	@Override
	public Boolean bindSocial(String type, String code) {
		String identify = loginHandlerMap.get(type).identify(code);
		SysUser sysUser = sysUserMapper.selectById(SecurityUtils.getUser().getId());
		sysUser.setWxOpenid(identify);
		sysUserMapper.updateById(sysUser);
		//更新緩存
		cacheManager.getCache("user_details").evict(sysUser.getUsername());
		return Boolean.TRUE;
	}

	/**
	 * 根据入参查询用户信息
	 *
	 * @param inStr TYPE@code
	 * @return
	 */
	@Override
	public UserInfo getUserInfo(String inStr) {
		String[] inStrs = inStr.split("@");
		String type = inStrs[0];
		String loginStr = inStrs[1];
		return loginHandlerMap.get(type).handle(loginStr);
	}
}
