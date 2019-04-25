package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredService;
import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredServiceWithBLOBs;
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
@Transactional // 测试类上使用@Transactional注解，自动回滚数据
public class CASClientAccessServiceTest {

    @Autowired
    private CASClientAccessService casClientAccessService;


    @Test
    public void pageSelect() {
        int page = 2;
        int row = 3;
        List<RegexRegisteredService> list = casClientAccessService.pageSelect(page, row);
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void totalNum() {
        int result = casClientAccessService.totalNum();
        Assert.assertNotEquals(result, 0);
    }

    @Test
    public void insertRecord() {
        RegexRegisteredServiceWithBLOBs regexRegisteredServiceWithBLOBs = new RegexRegisteredServiceWithBLOBs();
        regexRegisteredServiceWithBLOBs.setExpressionType("regex");
        regexRegisteredServiceWithBLOBs.setId(10006L);
        regexRegisteredServiceWithBLOBs.setEvaluationOrder(3);
        regexRegisteredServiceWithBLOBs.setName("test_department");
        regexRegisteredServiceWithBLOBs.setServiceid("http://192.168.1.123");
        regexRegisteredServiceWithBLOBs.setDescription("单元测试新增");
        regexRegisteredServiceWithBLOBs.setTheme("test_department");
        int result = casClientAccessService.insertRecord(regexRegisteredServiceWithBLOBs);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void update() {
        RegexRegisteredServiceWithBLOBs regexRegisteredServiceWithBLOBs = new RegexRegisteredServiceWithBLOBs();
        regexRegisteredServiceWithBLOBs.setId(10006L);
        regexRegisteredServiceWithBLOBs.setDescription("单元测试修改");
        regexRegisteredServiceWithBLOBs.setTheme("test_update");
        regexRegisteredServiceWithBLOBs.setName("test_update");
        int result = casClientAccessService.update(regexRegisteredServiceWithBLOBs);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void delete() {
        int id = 10006;
        int result = casClientAccessService.delete(id);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectAll() {
        List<RegexRegisteredService> list = casClientAccessService.selectAll();
        Assert.assertNotEquals(list.size(), 0);
    }

    @Test
    public void selectById() {
        int id = 10001;
        RegexRegisteredService regexRegisteredService = casClientAccessService.selectById(id);
        Assert.assertNotNull(regexRegisteredService);
    }

}