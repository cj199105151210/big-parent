package com.bosssoft.bigdata.config;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ApplicationContext;

/**
 * @program: bigdata
 * @description: 配置中心
 * @author: Mr.Lucky
 * @create: 2019-02-18 14:21
 **/
@EnableConfigServer
@SpringCloudApplication
@Slf4j
public class BossConfigApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BossConfigApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        log.info("-------------------------------------------------------");
        log.info("基础服务-服务配置中心启动完毕，请查看日志！");
        log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
        log.info("-------------------------------------------------------");
    }
}
