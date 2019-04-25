/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: FlowPositionService
 * Author:   Jack Ma
 * Date:     2019/3/11 15:30
 * Description: 流程岗位管理服务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.FlowPositionMapper;
import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.UserAndFlowMapper;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.FlowPosition;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndFlow;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlowPositionService {

    @Autowired
    FlowPositionMapper flowPositionMapper;
    @Autowired
    UserAndFlowMapper userAndFlowMapper;

    public List<FlowPosition> selectAll(FlowPosition flowPosition){
        return flowPositionMapper.selectAllByRecord(flowPosition);
    }

    /**
     * 根据id查询用户组信息
     *
     * @param id
     * @return
     */
    public FlowPosition selectByPrimaryKey(String id) {
        return flowPositionMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增节点
     *
     * @param flow
     * @return
     */
    public int insertNode(FlowPosition flow) {
        String id = UUID.randomUUID().toString().replace("-", "");
        flow.setGuid(id);
        flow.setIsDelete(0);
        return flowPositionMapper.insertSelective(flow);
    }

    /**
     * 更新节点
     *
     * @param flow
     * @return
     */
    public int updateNode(FlowPosition flow) {
        return flowPositionMapper.updateByPrimaryKeySelective(flow);
    }

    /**
     * 根据ID删除节点（逻辑删除）
     *
     * @param nodeId
     * @return
     */
    public int deleteNodeById(String nodeId) {
        // 根据Id查询信息
        FlowPosition flow = flowPositionMapper.selectByPrimaryKey(nodeId);
        flow.setIsDelete(1);
        // 删除节点下的人员信息
        List<UserMessageVo> list = flowPositionMapper.selectUserByNodeId(nodeId);
        if (list.size() != 0) {
            userAndFlowMapper.deletePeopleByNodeId(nodeId);
        }
        return flowPositionMapper.updateByPrimaryKeySelective(flow);
    }

    /**
     * 根据ID查询人员信息
     *
     * @param nodeId
     * @return
     */
    public List<UserMessageVo> selectUserByNodeId(String nodeId) {
        return flowPositionMapper.selectUserByNodeId(nodeId);
    }

    /**
     * 根据姓名查询人员信息
     *
     * @param xm
     * @return
     */
    public List<UserMessage> selectUserByXM(String xm) {
        return flowPositionMapper.selectUserByXM(xm);
    }

    /**
     * 新增用户与流程岗位关系数据
     * @param userAndFlow
     * @return
     */
    public int insertToRelation(UserAndFlow userAndFlow) {
        // 查询是否已经存在该数据
        UserAndFlow andFlow = userAndFlowMapper.selectUserRelation(userAndFlow);
        if (andFlow == null) {
            return userAndFlowMapper.insertSelective(userAndFlow);
        } else {
            return 0;
        }
    }

    /**
     * 删除用户与流程岗位关系数据
     * @param userAndFlow
     * @return
     */
    public int deleteFromRelation(UserAndFlow userAndFlow) {
        return userAndFlowMapper.deleteFromRelation(userAndFlow);
    }


    /**
     * 校验名称是否重复
     * @param name
     * @return
     */
    public int checkName(String name){
        List<FlowPosition> list = flowPositionMapper.selectByName(name);
        if (list.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 使用id查询用户流程岗位
     * @param userId
     * @return
     */
    public List selectFlowByUserId(String userId) {
        return userAndFlowMapper.selectFlowByUserId(userId);
    }

    /**
     * 关键词查询用户所在部门
     * @param userId
     * @param keyWord
     * @return
     */
    public List fuzzSearch(String userId, String keyWord) {
        return userAndFlowMapper.fuzzSearch(userId,keyWord);
    }
}
