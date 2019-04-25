/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: FlowPositionController
 * Author:   Jack Ma
 * Date:     2019/3/11 14:17
 * Description: 流程岗位管理controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.controller;

import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.user.center.service.FlowPositionService;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.FlowPosition;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndFlow;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flowPosition")
public class FlowPositionController {

    @Autowired
    FlowPositionService flowPositionService;


    /**
     * 新增节点
     *
     * @param flow
     * @return
     */
    @PostMapping("insertNode")
    public R insertNode(@RequestBody FlowPosition flow) {
        // 校验名称是否重复
        int flag = flowPositionService.checkName(flow.getCname());
        if (flag == 0) {
            // 新增数据
            return new R<>(flowPositionService.insertNode(flow));
        } else {
            return new R<>("msg", "is_exist");
        }
    }

    /**
     * 更新节点
     *
     * @param flow
     * @return
     */
    @PostMapping("updateNode")
    public R updateNode(@RequestBody FlowPosition flow) {
        // 校验名称是否重复
        int flag = flowPositionService.checkName(flow.getCname());
        if (flag == 0) {
            // 更新数据
            return new R<>(flowPositionService.updateNode(flow));
        } else {
            return new R<>("msg", "is_exist");
        }
    }

    /**
     * 根据ID删除节点（逻辑删除）
     *
     * @param nodeId
     * @return
     */
    @GetMapping("deleteNodeById")
    public R deleteNodeById(String nodeId) {
        return new R<>(flowPositionService.deleteNodeById(nodeId));
    }

    /**
     * 根据节点ID查询人员信息
     *
     * @param nodeId
     * @return
     */
    @GetMapping("selectUserByNodeId")
    public R selectUserByNodeId(String nodeId) {
        List<UserMessageVo> list = flowPositionService.selectUserByNodeId(nodeId);
        return new R<>(list);
    }

    /**
     * 根据姓名查询人员信息
     *
     * @param xm
     * @return
     */
    @GetMapping("selectUserByXM")
    public R selectUserByXM(String xm) {
        return new R<>(flowPositionService.selectUserByXM(xm));
    }

    /**
     * 新增流程岗位与用户关系数据
     * @param userAndFlow
     * @return
     */
    @PostMapping("insertToRelation")
    public R insertToRelation( UserAndFlow userAndFlow) {
        return new R<>(flowPositionService.insertToRelation(userAndFlow));
    }

    /**
     * 删除流程岗位与用户关系数据
     * @param userAndFlow
     * @return
     */
    @PostMapping("deleteFromRelation")
    public R deleteFromRelation( UserAndFlow userAndFlow) {
        return new R<>(flowPositionService.deleteFromRelation(userAndFlow));
    }

    /**
     * 获取全部流程岗位
     * @return
     */
    @GetMapping("getAll")
    public R getAll(){
        FlowPosition position = new FlowPosition();
        position.setIsDelete(0);
        List<FlowPosition> list = flowPositionService.selectAll(position);

        return new R<>(list);
    }
    /**
     * 使用id查询所有所在流程岗位
     * @param userId
     * @return
     */
    @GetMapping("/selectFlowByUserId")
    public  R  selectFlowByUserId(String userId){
        List list = flowPositionService.selectFlowByUserId(userId);
        return new R(list);
    }

    /**
     * 根据关键字和用户id模糊查询，用户所属部门
     * @param userId
     * @param keyWord
     * @return
     */
    @GetMapping("/fuzzSearch")
    public R fuzzSearch(@Param("userId")String  userId, @Param("keyWord")String keyWord){
        List list = flowPositionService.fuzzSearch(userId,keyWord);
        return new R(list);
    }

    /**
     * 根据节点ID分页查询人员信息
     *
     * @param nodeId 节点id
     * @param currentPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("selectUserPageByNodeId")
    public R selectUserPageByNodeId(@RequestParam(value = "nodeId", required = true) String nodeId,
                                int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<UserMessageVo> list = flowPositionService.selectUserByNodeId(nodeId);
        PageInfo<UserMessageVo> pageInfo = new PageInfo<>(list);
        return new R<>(pageInfo);
    }

    /**
     * 根据ID查询人员信息（不分页）
     *
     * @param nodeId 节点id
     * @return
     */
    @GetMapping("selectAllUserByNodeId")
    public R selectUserPageByNodeId(@RequestParam(value = "nodeId", required = true) String nodeId) {
        List<UserMessageVo> list = flowPositionService.selectUserByNodeId(nodeId);
        return new R<>(list);
    }

    /**
     * 根据id查询用户组信息
     * @param nodeId
     * @return
     */
    @GetMapping("selectFlowPositionById")
    public R selectFlowPositionById(String nodeId){
        FlowPosition flowPosition = flowPositionService.selectByPrimaryKey(nodeId);
        return new R(flowPosition);
    }
}
