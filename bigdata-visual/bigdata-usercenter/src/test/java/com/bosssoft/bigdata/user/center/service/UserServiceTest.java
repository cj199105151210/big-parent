package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //测试类中使用@Transactioal注解，会自动回滚数据。
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void updatePassword() {
        UserMessage user = new UserMessage();
        user.setLoginName("bigboss");
        user.setPassword("9999");
        int result = userService.updatePassword(user);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void increasePasswordByLoginName() {
        String loginName = "bigboss";
        int result = userService.increasePasswordByLoginName(loginName);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectByLoginName() {
        String loginName = "bigboss";
        UserMessage user = userService.selectByLoginName(loginName);
        Assert.assertEquals(user.getLoginName(), "bigboss");
    }

    @Test
    public void insertUser() {
        UserMessage user = new UserMessage();
        String guid = UUID.randomUUID().toString().replace("-", "");
        user.setGuid(guid);
        user.setLoginName("LiXue");
        int result = userService.insertUser(user);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteById() {
        String guid = "99999";
        int result = userService.deleteById(guid);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void delete() {
        String guids = "99999,88888";
        int result = userService.delete(guids);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void updateUser() {
        UserMessage user = new UserMessage();
        user.setGuid("99999");
        user.setLoginName("nihao");
        int result = userService.updateUser(user);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserMessage() {
        String guid = "99999";
        UserMessage user = userService.selectUserMessage(guid);
        Assert.assertNotNull(user);
    }

    @Test
    public void selectUser() {
        String loginName = "bigboss";
        UserMessage user = userService.selectUser(loginName);
        Assert.assertNotNull(user);
    }

    @Test
    public void restart() {
        String guid = "88888";
        int result = userService.restart(guid);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserForPage() {
        List<UserMessage> list = userService.selectUserForPage("1", "龙");
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectUserByNamePhone() {
        String queryParam = "m";
        List<UserMessageVo> list = userService.selectUserByNamePhone(queryParam);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void updUserFirstLogin() {
        String loginName = "bigboss";
        int result = userService.updUserFirstLogin(loginName);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectByUserName() {
        String loginName = "bigboss";
        UserMessage user = userService.selectByUserName(loginName);
        Assert.assertNotNull(user);
    }
}