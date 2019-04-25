package com.bosssoft.bigdata.admin;


import java.util.Arrays;
import com.bosssoft.bigdata.common.security.annotation.EnableBosxFeignClients;
import com.bosssoft.bigdata.common.security.annotation.EnableBosxResourceServer;
import com.bosssoft.bigdata.common.swagger.annotation.EnableBosxSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author lucky
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@EnableBosxSwagger2
@SpringCloudApplication
@EnableBosxFeignClients
@EnableBosxResourceServer
@Slf4j
public class BossAdminApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BossAdminApplication.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		log.info("-------------------------------------------------------");
		log.info("基础服务-服务用户统一管理系统启动完毕，请查看日志！");
		log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
		log.info("-------------------------------------------------------");
	}

}
