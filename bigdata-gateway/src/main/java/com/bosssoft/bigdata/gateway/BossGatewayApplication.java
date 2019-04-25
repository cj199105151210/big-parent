package com.bosssoft.bigdata.gateway;

import java.util.Arrays;
import com.bosssoft.bigdata.common.gateway.annotation.EnableBosxDynamicRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: gateway
 * @description: 网关应用
 * @author: Mr.Lucky
 * @create: 2019-02-18 14:29
 **/
@EnableBosxDynamicRoute
@SpringCloudApplication
@RestController
@Slf4j
public class BossGatewayApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BossGatewayApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        log.info("-------------------------------------------------------");
        log.info("基础服务-服务网关应用中心启动完毕，请查看日志！");
        log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
        log.info("-------------------------------------------------------");
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello jeck";
    }

}
