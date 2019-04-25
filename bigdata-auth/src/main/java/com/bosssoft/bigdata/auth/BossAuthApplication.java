package com.bosssoft.bigdata.auth;

import java.util.Arrays;
import com.bosssoft.bigdata.common.security.annotation.EnableBosxFeignClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Lucky
 * @Description:认证授权中心
 * @Date: Created in 22:52 2019/2/23
 * @Modified By:
 */
@SpringCloudApplication
@EnableBosxFeignClients
@RestController
@Slf4j
public class BossAuthApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BossAuthApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        log.info("-------------------------------------------------------");
        log.info("基础服务-服务认证授权中心启动完毕，请查看日志！");
        log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
        log.info("-------------------------------------------------------");
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello authorization";
    }
}