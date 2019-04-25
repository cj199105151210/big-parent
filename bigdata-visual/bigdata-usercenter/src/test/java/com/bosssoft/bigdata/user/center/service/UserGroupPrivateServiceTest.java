package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndPrivateGroup;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroupPrivate;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //测试类上使用@Transactional注解，自动回滚数据。
public class UserGroupPrivateServiceTest {

    @Autowired
    private UserGroupPrivateService userGroupPrivateService;


    @Test
    public void selectByPrimaryKey() {
        String id = "5";
        UserGroupPrivate userGroupPrivate = userGroupPrivateService.selectByPrimaryKey(id);
        Assert.assertNotNull(userGroupPrivate);
    }

    @Test
    public void insertNode() {
        UserGroupPrivate userGroupPrivate = new UserGroupPrivate();
        userGroupPrivate.setCname("前端测试组");
        userGroupPrivate.setCode("qdcsz");
        userGroupPrivate.setName("afterTestGroup");
        userGroupPrivate.setCreateTime(new Date());
        userGroupPrivate.setIsDelete(0);
        int result = userGroupPrivateService.insertNode(userGroupPrivate);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void updateNode() {
        UserGroupPrivate userGroupPrivate = new UserGroupPrivate();
        userGroupPrivate.setGuid("5");
        userGroupPrivate.setCname("Vue前端");
        userGroupPrivate.setCode("testtest");
        userGroupPrivate.setName("Vue");
        int result = userGroupPrivateService.updateNode(userGroupPrivate);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteNodeById() {
        String id = "5";
        int result = userGroupPrivateService.deleteNodeById(id);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserByNodeId() {
        String nodeId = "8";
        List<UserMessageVo> list = userGroupPrivateService.selectUserByNodeId(nodeId);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectUserByXM() {
        String xm = "赵";
        List<UserMessage> list = userGroupPrivateService.selectUserByXM(xm);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void insertToRelation() {
        UserAndPrivateGroup userAndPrivateGroup = new UserAndPrivateGroup();
        userAndPrivateGroup.setUserId("88888");
        userAndPrivateGroup.setOrgId("8");
        int result = userGroupPrivateService.insertToRelation(userAndPrivateGroup);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteFromRelation() {
        UserAndPrivateGroup userAndPrivateGroup = new UserAndPrivateGroup();
        userAndPrivateGroup.setOrgId("9");
        userAndPrivateGroup.setUserId("1001");
        int result = userGroupPrivateService.deleteFromRelation(userAndPrivateGroup);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void checkName() {
        String cname = "前端测试组";
        int result = userGroupPrivateService.checkName(cname);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserByNamePhone() {
        String queryParam = "8";
        List<UserMessageVo> list = userGroupPrivateService.selectUserByNamePhone(queryParam);
        Assert.assertNotEquals(list.size(), 0);
    }
}