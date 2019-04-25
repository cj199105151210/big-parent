package com.bosssoft.bigdata.auth.handler;

import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.constant.enums.LoginErrorEnum;
import com.bosssoft.bigdata.common.security.handler.AbstractAuthenticationFailureEvenHandler;
import com.bosssoft.bigdata.common.security.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @Author: Lucky
 * @Description:
 * @Date: Created in 23:30 2019/2/23
 * @Modified By:
 */
@Slf4j
@Component
public class BosxAuthenticationFailureEvenHandler extends AbstractAuthenticationFailureEvenHandler {


	RedisUtils redisUtils;

	/**
	 * 处理登录失败方法
	 * <p>
	 *
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication          登录的authenticationException 对象
	 */
	@Override
	public void handle(AuthenticationException authenticationException, Authentication authentication) {
		// TODO 待验证效果 代表密码错误 增加错误次数
		if (LoginErrorEnum.PASSWORD_ERROR.getDesc().equals(authenticationException.getMessage())){
			//代表密码错误
			//增加错误次数
			Object error = redisUtils.get(CommonConstants.BOSX_PASSWORD_PREFIX + authentication.getPrincipal().toString());
			Integer error_num = null;
			if (error==null){
				error_num = 0;
			}else {
				error_num = (Integer) error;
			}
			redisUtils.set(CommonConstants.BOSX_PASSWORD_PREFIX+authentication.getPrincipal().toString(), error_num+1, CommonConstants.expire*60);


		}
		log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());
	}
}
