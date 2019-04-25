package com.bosssoft.bigdata.user.center;

import com.bosssoft.bigdata.common.security.annotation.EnableBosxResourceServer;
import com.bosssoft.bigdata.common.swagger.annotation.EnableBosxSwagger2;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@EnableBosxSwagger2 //接口文档控件添加
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableFeignClients(basePackages = {"com.bosssoft.bigdata.usercenter.api"})
//@MapperScan("com.bosssoft.bigdata.user.center.mapper")
@EnableBosxResourceServer
@Slf4j //日志
public class BigdataUserCenterApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BigdataUserCenterApplication.class, args);
        String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
        log.info("-------------------------------------------------------");
        log.info("基础服务-服务用户中心启动完毕，请查看日志！");
        log.info("当前使用的Profile(s)：" + Arrays.asList(activeProfiles));
        log.info("-------------------------------------------------------");
    }


}
