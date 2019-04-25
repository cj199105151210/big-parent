package com.bosssoft.bigdata.eureka;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;

/**
 * @program: bigdata
 * @description: 服务中心
 * @author: Mr.Lucky
 * @create: 2019-02-18 14:15
 **/
@EnableEurekaServer
@SpringBootApplication
@Slf4j
public class BossEurekaApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BossEurekaApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        log.info("-------------------------------------------------------");
        log.info("基础服务-服务注册中心启动完毕，请查看日志！");
        log.info("访问本地地址：http://localhost:8761");
        log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
        log.info("-------------------------------------------------------");
    }
}