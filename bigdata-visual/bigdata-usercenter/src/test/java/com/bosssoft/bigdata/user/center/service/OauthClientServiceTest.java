package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.usercenter.api.entity.SysOauthClientDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 测试类上使用此注解可自动回滚数据。
public class OauthClientServiceTest {

    @Autowired
    private OauthClientService oauthClientService;


    @Test
    public void insertRecord() {
        SysOauthClientDetails sysOauthClientDetails = new SysOauthClientDetails();
        sysOauthClientDetails.setClientId("123");
        sysOauthClientDetails.setClientSecret("123");
        sysOauthClientDetails.setScope("server");
        sysOauthClientDetails.setAutoapprove("true");
        sysOauthClientDetails.setTenantId(1);
        int result = oauthClientService.insertRecord(sysOauthClientDetails);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void update() {
        SysOauthClientDetails sysOauthClientDetails = new SysOauthClientDetails();
        sysOauthClientDetails.setClientId("456");
        sysOauthClientDetails.setClientSecret("456");
        String oldClientId = "123";
        int result = oauthClientService.update(sysOauthClientDetails, oldClientId);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void delete() {
        String clientId = "456";
        int result = oauthClientService.delete(clientId);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectAll() {
        List<SysOauthClientDetails> list = oauthClientService.selectAll();
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectById() {
        String clientId = "test";
        SysOauthClientDetails sysOauthClientDetails = oauthClientService.selectById(clientId);
        Assert.assertNotNull(sysOauthClientDetails);
    }

}