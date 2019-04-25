package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.model.UserDepartment;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.FlowPosition;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndFlow;
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
@Transactional // 测试类上使用注解@Transactional注解，自动回滚数据
public class FlowPositionServiceTest {

    @Autowired
    private FlowPositionService flowPositionService;


    @Test
    public void selectAll() {
        FlowPosition flowPosition = new FlowPosition();
        List<FlowPosition> list = flowPositionService.selectAll(flowPosition);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectByPrimaryKey() {
        String id = "10";
        FlowPosition flowPosition = flowPositionService.selectByPrimaryKey(id);
        Assert.assertNotNull(flowPosition);
    }

    @Test
    public void insertNode() {
        FlowPosition flowPosition = new FlowPosition();
        flowPosition.setCname("学生会主席");
        flowPosition.setCode("12306");
        flowPosition.setName("studentChairman");
        flowPosition.setShortName("会长");
        flowPosition.setCreateTime(new Date());
        int result = flowPositionService.insertNode(flowPosition);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void updateNode() {
        FlowPosition flowPosition = new FlowPosition();
        flowPosition.setGuid("6");
        flowPosition.setCname("教务部长");
        flowPosition.setCode("61534");
        flowPosition.setName("jwbz");
        flowPosition.setUpdateTme(new Date());
        int result = flowPositionService.updateNode(flowPosition);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteNodeById() {
        String id = "4";
        int result = flowPositionService.deleteNodeById(id);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectUserByNodeId() {
        String nodeId = "10";
        List<UserMessageVo> list = flowPositionService.selectUserByNodeId(nodeId);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectUserByXM() {
        String xm = "德";
        List<UserMessage> list = flowPositionService.selectUserByXM(xm);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void insertToRelation() {
        UserAndFlow userAndFlow = new UserAndFlow();
        userAndFlow.setOrgId("8");
        userAndFlow.setUserId("88888");
        int result = flowPositionService.insertToRelation(userAndFlow);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void deleteFromRelation() {
        UserAndFlow userAndFlow = new UserAndFlow();
        userAndFlow.setOrgId("8a7385980aea4f6fba1552af6e322172");
        userAndFlow.setUserId("1dea0e7a08714e7d94f3ef59e17818eb");
        int result = flowPositionService.deleteFromRelation(userAndFlow);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void checkName() {
        String name = "院长";
        int result = flowPositionService.checkName(name);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectFlowByUserId() {
        String userId = "1002";
        List<UserAndFlow> list = flowPositionService.selectFlowByUserId(userId);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void fuzzSearch() {
        String userId = "1002";
        String keyWord = "主任";
        List<UserDepartment> list = flowPositionService.fuzzSearch(userId, keyWord);
        Assert.assertNotEquals(list.size(), 0);
    }
}