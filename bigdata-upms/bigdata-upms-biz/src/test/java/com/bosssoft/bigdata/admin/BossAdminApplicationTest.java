package com.bosssoft.bigdata.admin;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

/**
 * @program: bosx
 * @description: 加解密单元测试
 * @author: Mr.Lucky
 * @create: 2019-02-18 22:50
 **/
public class BossAdminApplicationTest {
    @Test
    public void testJasyptBoss() {
//        String bos="bos";
//        getJasyptBoss(bos);
//        String daemon="daemon";
//        getJasyptBoss(daemon);
//        String gen="gen";
//        getJasyptBoss(gen);
//        String test="test";
//        getJasyptBoss(test);
        String bosx="bosx";
        getJasyptBoss(bosx);

    }

    public void getJasyptBoss(String name){
        // 对应application-dev.yml 中配置的根密码
        System.setProperty("jasypt.encryptor.password", "bosssoft");
        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
        //加密方法
        String encrypt = stringEncryptor.encrypt(name);
        System.out.println(name+"解密方法:"+encrypt);
        //解密方法 bosx解密方法:bosx
        String decrypt = stringEncryptor.decrypt(encrypt);
        System.out.println(name+"解密方法:"+decrypt);
        System.out.println("---------------------------------");
    }
    @Test
    public void getJasyptBoss2(){
        // 对应application-dev.yml 中配置的根密码
        System.setProperty("jasypt.encryptor.password", "boss");
        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());

        //解密方法 bosx解密方法:bosx
        String decrypt = stringEncryptor.decrypt("VPK9vdejlgPAkEnV50+bbQ==");
        System.out.println("解密方法:"+decrypt);
        System.out.println("---------------------------------");
    }

}
