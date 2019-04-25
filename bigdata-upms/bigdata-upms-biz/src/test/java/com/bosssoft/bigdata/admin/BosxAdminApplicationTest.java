package com.bosssoft.bigdata.admin;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: Lucky
 * @Description:
 * @Date: Created in 22:57 2019/2/27
 * @Modified By:
 */
public class BosxAdminApplicationTest {
    /**
     * jasypt.encryptor.password 对应 配置中心 application-dev.yml 中的密码
     */
    @Test
    public void testEnvironmentProperties() {
        System.setProperty("jasypt.encryptor.password", "boss");
        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
        //加密方法
        System.out.println(stringEncryptor.encrypt("123456"));
        //解密方法
        System.out.println(stringEncryptor.decrypt("VZ5XhsD3fxa+1NxkSmwp4w=="));
    }
}