package com.bosssoft.bigdata.admin;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

import java.util.Base64;

/**
 * @Author: Lucky
 * @Description: 针对前端自定义basic认证工具，后台加入后端管理门户
 * @Date: Created in 22:57 2019/2/27
 * @Modified By:
 */
public class Base64EncodingApplicationTest {
    /**
     * jasypt.encryptor.password 对应 配置中心 application-dev.yml 中的密码
     */
    @Test
    public void testBase64Encoding() {
        //String base64Encoding = Base64.getEncoder().encodeToString("".getBytes());
        System.out.println(new String(Base64.getEncoder().encode("app:app".getBytes())));

    }
}