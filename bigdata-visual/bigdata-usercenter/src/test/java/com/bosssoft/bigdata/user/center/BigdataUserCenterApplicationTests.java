package com.bosssoft.bigdata.user.center;

import com.alibaba.fastjson.JSONObject;
import com.bosssoft.bigdata.user.center.service.UserService;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BigdataUserCenterApplication.class)
public class BigdataUserCenterApplicationTests {


    @Autowired
    UserService userService;

    @Test
    public void testLoginUser(){
        UserMessage admin = userService.selectByLoginName("lucky");

        System.out.println(JSONObject.toJSONString(admin));

    }


}
