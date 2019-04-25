package com.bosssoft.bigdata.daemon.quartz;

import com.bosssoft.bigdata.common.security.annotation.EnableBosxFeignClients;
import com.bosssoft.bigdata.common.security.annotation.EnableBosxResourceServer;
import com.bosssoft.bigdata.common.swagger.annotation.EnableBosxSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 * 定时任务模块
 */
@EnableBosxSwagger2
@SpringCloudApplication
@EnableBosxFeignClients
@EnableBosxResourceServer
@Slf4j
public class BossDaemonQuartzApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BossDaemonQuartzApplication.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		log.info("-------------------------------------------------------");
		log.info("模块服务-定时任务模块启动完毕，请查看日志！");
		log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
		log.info("-------------------------------------------------------");
	}
}
