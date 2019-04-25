package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.model.RelationParam;
import com.bosssoft.bigdata.user.center.model.Tree;
import com.bosssoft.bigdata.user.center.model.UserDepartment;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndGroup;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserGroupServiceTest {

    @Autowired
    private UserGroupService userGroupService;


    @Test
    public void selectUserGroupByParentId() {
        String pId = "0";
        List<UserGroup> list = userGroupService.selectUserGroupByParentId(pId);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectAllUserGroup() {
        UserGroup userGroup = new UserGroup();
        List<UserGroup> list = userGroupService.selectAllUserGroup(userGroup);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectByPrimaryKey() {
        String id = "1";
        UserGroup userGroup = userGroupService.selectByPrimaryKey(id);
        Assert.assertNotNull(userGroup);
    }

    @Test
    public void checkName() {
        String cname = "科研组";
        int result = userGroupService.checkName(cname);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void insertNode() {
        UserGroup userGroup = new UserGroup();
        String id = UUID.randomUUID().toString().replace("-", "");
        userGroup.setGuid(id);
        userGroup.setIsDelete(0);
        userGroup.setCname("系统组测试1");
        userGroup.setCreateTime(new Date());
        int result = userGroupService.insertNode(userGroup);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void updateNode() {
        UserGroup userGroup = new UserGroup();
        userGroup.setGuid("1");
        userGroup.setCname("系统组测试2");
        int result = userGroupService.updateNode(userGroup);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteNodeById() {
        String id = "1";
        int result = userGroupService.deleteNodeById(id);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserByNodeId() {
        String id = "3";
        List<UserMessageVo> list = userGroupService.selectUserByNodeId(id);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectUserByTypeXM() {
        String type = "1";
        String xm = "西";
        List<UserMessageVo> list = userGroupService.selectUserByTypeXM(type, xm);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void deletePeopleByNodeId() {
        String id = "5";
        int result = userGroupService.deletePeopleByNodeId(id);
        Assert.assertNotEquals(result, 0);
    }

    @Test
    public void selectAllTreeData() {
        String type = "2";
        List<Tree> list = userGroupService.selectAllTreeData(type, null);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectAllUsers() {
        String type = "2";
        String id = "3";
        List<Tree> list = userGroupService.selectAllUsers(type, id);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void insertToRelation() {
        UserAndGroup userAndGroup = new UserAndGroup();
        userAndGroup.setUserId("99999");
        userAndGroup.setOrgId("9");
        int result = userGroupService.insertToRelation(userAndGroup);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteFromRelation() {
        UserAndGroup userAndGroup = new UserAndGroup();
        userAndGroup.setUserId("1002");
        userAndGroup.setOrgId("3");
        int result = userGroupService.deleteFromRelation(userAndGroup);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectTreeDataByNodeId() {
        String type = "2";
        String id = "4";
        List<Tree> list = userGroupService.selectTreeDataByNodeId(type, id);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectUserGroup() {
        String userId = "1002";
        List<UserDepartment> list = userGroupService.selectUserGroup(userId);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void fuzzSearch() {
        String userId = "af60ff909bf0425cbb97bb32634c9a80";
        String keyWord = "安徽";
        List<UserDepartment> list = userGroupService.fuzzSearch(userId, keyWord);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void adjustRelation() {
        RelationParam param = new RelationParam();
        UserAndOrg userAndOrg = new UserAndOrg();
        param.setAdjustType("0");
        param.setType("1");
        param.setTargetType("1");
        param.setTargetId("3");
        param.setUserId("ac32551d7a3e42009566d7014d5cb194");
        param.setOrgId("461408dba04c4fac97acbcbb08f6e129");

        userAndOrg.setUserId("ac32551d7a3e42009566d7014d5cb194");
        userAndOrg.setOrgId("461408dba04c4fac97acbcbb08f6e129");

        int result = userGroupService.adjustRelation(param, userAndOrg);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserByParentId() {
        String type = "2";
        String id = "11";
        List<UserMessageVo> list = userGroupService.selectUserByParentId(type, id);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectAllUserGroupList() {
        List<UserGroup> list = userGroupService.selectAllUserGroupList();
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectUserByNamePhone() {
        String queryParam = "d";
        List<UserMessageVo> list = userGroupService.selectUserByNamePhone(queryParam);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectUserInfoByType() {
        String type = "2";
        List<UserMessageVo> list = userGroupService.selectUserInfoByType(type);
        Assert.assertNotEquals(list.size(), 0);
    }
}