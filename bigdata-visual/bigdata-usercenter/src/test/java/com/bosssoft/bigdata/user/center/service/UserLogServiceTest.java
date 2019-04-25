package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //测试类中使用@Transactionl自动数据回滚
public class UserLogServiceTest {

    @Autowired
    private UserLogService userLogService;


    @Test
    public void insertUserLog() {
        UserLog userLog = new UserLog();
        userLog.setGuid("6666");
        userLog.setLoginType(2);
        userLog.setOptTime(new Date());
        userLog.setUserName("bosi");

        UserLog log = userLogService.insertUserLog(userLog);
        Assert.assertEquals(log.getUserName(), "bosi");
    }

    @Test
    public void findByLoginType() {
        int type = 2;
        Pageable pageable = new PageRequest(2, 3);
        Page<UserLog> page = userLogService.findByLoginType(type, pageable);
        Assert.assertNotEquals(page.getTotalPages(), 0);
    }

    @Test
    public void findByUserName() {
        String userName = "user3";
        Pageable pageable = new PageRequest(2, 3);
        Page<UserLog> page = userLogService.findByUserName(userName, pageable);
        Assert.assertNotEquals(page.getContent().size(), 0);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(5, 15);
        Page<UserLog> page = userLogService.findAll(pageRequest);
        Assert.assertNotEquals(page.getContent().size(), 0);
    }

    @Test
    public void findByUserNameAndLoginType() {
        int type = 2;
        String userName = "user2";
        Pageable pageable = new PageRequest(2, 4);
        Page<UserLog> page = userLogService.findByUserNameAndLoginType(userName, type, pageable);
        Assert.assertNotEquals(page.getContent().size(), 0);
    }
}