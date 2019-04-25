package com.bosssoft.bigdata.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * @program: gateway
 * @description: 分布式事物
 * @author: Mr.Lucky
 * @create: 2019-02-22 14:29
 **/
@SpringCloudApplication
@Slf4j
public class BossTxManagerApplication {


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BossTxManagerApplication.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		log.info("-------------------------------------------------------");
		log.info("基础服务-分布式事物模块中心启动完毕，请查看日志！");
		log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
		log.info("-------------------------------------------------------");
	}

}
