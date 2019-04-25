package com.bosssoft.bigdata.admin.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bosssoft.bigdata.admin.api.dto.UserInfo;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import com.bosssoft.bigdata.admin.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lucky
 * @date 2018/11/18
 */
@Slf4j
@Component("SMS")
@AllArgsConstructor
public class SmsLoginHandler extends AbstractLoginHandler {
	private final SysUserService sysUserService;


	/**
	 * 验证码登录传入为手机号
	 * 不用不处理
	 *
	 * @param mobile
	 * @return
	 */
	@Override
	public String identify(String mobile) {
		return mobile;
	}

	/**
	 * 通过mobile 获取用户信息
	 *
	 * @param identify
	 * @return
	 */
	@Override
	public UserInfo info(String identify) {
		SysUser user = sysUserService
				.getOne(Wrappers.<SysUser>query()
						.lambda().eq(SysUser::getPhone, identify));

		if (user == null) {
			log.info("手机号未注册:{}", identify);
			return null;
		}
		return sysUserService.findUserInfo(user);
	}
}
