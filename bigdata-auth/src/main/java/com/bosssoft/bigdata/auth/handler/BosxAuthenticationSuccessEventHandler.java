package com.bosssoft.bigdata.auth.handler;

import com.bosssoft.bigdata.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Author: Lucky
 * @Description:
 * @Date: Created in 23:30 2019/2/23
 * @Modified By:
 */
@Slf4j
@Component
public class BosxAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

//	@Autowired
//	SaveUserLogThread saveUserLogThread;


	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 *
	 * @param authentication 登录对象
	 */
	@Override
	public void handle(Authentication authentication) {
		log.info("用户：{} 登录成功", authentication.getPrincipal());
//		//记录用户操作日志
//		UserLog log = new UserLog();
//		log.setOptTime(new Date());
//		log.setUserName(authentication.getName());
//		log.setLoginType(2);//web登录
//		log.setOptType(1);//登录
//		saveUserLogThread.addData(log);
	}
}
