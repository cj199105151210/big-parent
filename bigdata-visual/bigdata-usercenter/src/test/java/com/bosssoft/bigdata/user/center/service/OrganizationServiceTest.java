package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.model.UserDepartment;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.Organization;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
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
@Transactional //测试类使用注解@Transactional，会对数据库操作自动回滚
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;


    @Test
    public void selectAllByRecord() {
        Organization organization = new Organization();
        List<Organization> list = organizationService.selectAllByRecord(organization);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void findUserDirectOrg() {
        String userId = "1001";
        Organization organization = organizationService.findUserDirectOrg(userId);
        Assert.assertNotNull(organization);
    }

    @Test
    public void insert() {
        Organization organization = new Organization();
        organization.setCname("学生会组织");
        organization.setName("studentOrg");
        organization.setCode("5-7-1");
        organization.setShortName("学生会");
        organization.setSortNo(1);
        organization.setCreateTime(new Date());
        int result = organizationService.insert(organization);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void update() {
        Organization organization = new Organization();
        organization.setGuid("11");
        organization.setCname("卫生管理部门");
        organization.setName("sanitationManage");
        organization.setShortName("卫生部");
        organization.setSortNo(5);
        int result = organizationService.update(organization);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void delete() {
        String id = "11";
        int result = organizationService.delete(id);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserByNodeId() {
        String nodeId = "5";
        List<UserMessageVo> list = organizationService.selectUserByNodeId(nodeId);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectOrgMessage() {
        String orgId = "6";
        Organization organization = organizationService.selectOrgMessage(orgId);
        Assert.assertNotNull(organization);
    }

    @Test
    public void deletePeopleByNodeId() {
        String id = "1";
        int result = organizationService.deletePeopleByNodeId(id);
        Assert.assertNotEquals(result, 0);
    }

    @Test
    public void selectUserByXM() {
        String xm = "张";
        List<UserMessage> list = organizationService.selectUserByXM(xm);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void insertToRelation() {
        UserAndOrg userAndOrg = new UserAndOrg();
        userAndOrg.setOrgId("7");
        userAndOrg.setUserId("888888");
        int result = organizationService.insertToRelation(userAndOrg);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteFromRelation() {
        UserAndOrg userAndOrg = new UserAndOrg();
        userAndOrg.setOrgId("5");
        userAndOrg.setUserId("1002");
        int result = organizationService.deleteFromRelation(userAndOrg);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserOrg() {
        String userId = "7cf46d346212423998fb3ebc77ab6015";
        List<UserDepartment> list = organizationService.selectUserOrg(userId);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void updateDirectly() {
        UserAndOrg userAndOrg = new UserAndOrg();
        userAndOrg.setOrgId("5");
        userAndOrg.setUserId("1001");
        int result = organizationService.updateDirectly(userAndOrg);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void checkName() {
        Organization organization = new Organization();
        String checkType = "0";
        organization.setCname("校长办公室");
        int result = organizationService.checkName(organization, checkType);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void fuzzSearch() {
        String userId = "83beb5aae90046baaadf8ece7fcb9a77";
        String keyWord = "办公室";
        List<UserDepartment> list = organizationService.fuzzSearch(userId, keyWord);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void checkCode() {
        Organization organization = new Organization();
        String checkType = "1";
        organization.setGuid("416e89e916184cc1b04333d3b138fae5");
        organization.setCode("9");
        int result = organizationService.checkCode(organization, checkType);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserByNamePhone() {
        String orgId = "5";
        String queryParam = "龙";
        List<UserMessageVo> list = organizationService.selectUserByNamePhone(orgId, queryParam);
        Assert.assertNotEquals(list.size(), 0);
    }
}