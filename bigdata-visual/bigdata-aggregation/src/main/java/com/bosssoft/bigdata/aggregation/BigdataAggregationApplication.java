package com.bosssoft.bigdata.aggregation;

import com.bosssoft.bigdata.common.security.annotation.EnableBosxFeignClients;
import com.bosssoft.bigdata.common.security.annotation.EnableBosxResourceServer;
import com.bosssoft.bigdata.common.swagger.annotation.EnableBosxSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * @author LleytonJ
 * @since 2019-03-02
 * 大数据中心聚合平台
 */
@EnableBosxSwagger2
@SpringCloudApplication
@EnableBosxFeignClients
@EnableBosxResourceServer
@Slf4j
public class BigdataAggregationApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BigdataAggregationApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        log.info("-------------------------------------------------------");
        log.info("模块服务-大数据中心聚合平台启动完毕，请查看日志！");
        log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
        log.info("-------------------------------------------------------");
    }

}
