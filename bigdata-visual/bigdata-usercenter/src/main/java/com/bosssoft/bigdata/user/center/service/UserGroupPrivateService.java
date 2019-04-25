/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: UserGroupPrivateService
 * Author:   Jack Ma
 * Date:     2019/3/14 11:30
 * Description: 个人管理服务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.UserAndPrivateGroupMapper;
import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.UserGroupPrivateMapper;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.common.data.annotation.CodeToData;
import com.bosssoft.bigdata.usercenter.api.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserGroupPrivateService {

    @Autowired
    UserGroupPrivateMapper userGroupPrivateMapper;
    @Autowired
    UserAndPrivateGroupMapper userAndPrivateGroupMapper;


    /**
     * 根据id查询用户组信息
     *
     * @param id
     * @return
     */
    public UserGroupPrivate selectByPrimaryKey(String id) {
        return userGroupPrivateMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增节点
     *
     * @param groupPrivate
     * @return
     */
    public int insertNode(UserGroupPrivate groupPrivate) {
        String id = UUID.randomUUID().toString().replace("-", "");
        groupPrivate.setGuid(id);
        groupPrivate.setIsDelete(0);
        return userGroupPrivateMapper.insertSelective(groupPrivate);
    }

    /**
     * 更新节点
     *
     * @param groupPrivate
     * @return
     */
    public int updateNode(UserGroupPrivate groupPrivate) {
        return userGroupPrivateMapper.updateByPrimaryKeySelective(groupPrivate);
    }

    /**
     * 根据ID删除节点（逻辑删除）
     *
     * @param nodeId
     * @return
     */
    public int deleteNodeById(String nodeId) {
        // 根据Id查询信息
        UserGroupPrivate groupPrivate = userGroupPrivateMapper.selectByPrimaryKey(nodeId);
        groupPrivate.setIsDelete(1);
        // 删除节点下的人员信息
        List<UserMessageVo> list = userGroupPrivateMapper.selectUserByNodeId(nodeId);
        if (list.size() != 0) {
            userAndPrivateGroupMapper.deletePeopleByNodeId(nodeId);
        }
        return userGroupPrivateMapper.updateByPrimaryKeySelective(groupPrivate);
    }

    /**
     * 根据ID查询人员信息
     *
     * @param nodeId
     * @return
     */
    public List<UserMessageVo> selectUserByNodeId(String nodeId) {
        return userGroupPrivateMapper.selectUserByNodeId(nodeId);
    }

    /**
     * 根据姓名查询人员信息
     *
     * @param xm
     * @return
     */
    public List<UserMessage> selectUserByXM(String xm) {
        return userGroupPrivateMapper.selectUserByXM(xm);
    }

    /**
     * 新增用户与用户组关系数据
     * @param userAndPrivateGroup
     * @return
     */
    public int insertToRelation(UserAndPrivateGroup userAndPrivateGroup) {
        // 查询是否已经存在该数据
        UserAndPrivateGroup andPrivateGroup = userAndPrivateGroupMapper.selectUserRelation(userAndPrivateGroup);
        if (andPrivateGroup == null) {
            return userAndPrivateGroupMapper.insertSelective(userAndPrivateGroup);
        } else {
            return 0;
        }
    }

    /**
     * 删除用户与用户组关系数据
     * @param userAndPrivateGroup
     * @return
     */
    public int deleteFromRelation(UserAndPrivateGroup userAndPrivateGroup) {
        return userAndPrivateGroupMapper.deleteFromRelation(userAndPrivateGroup);
    }

    /**
     * 校验名称是否重复
     * @param name
     * @return
     */
    public int checkName(String name){
        List<UserGroupPrivate> list = userGroupPrivateMapper.selectByName(name);
        if (list.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 根据姓名、姓名首字母和电话查询用户组下用户信息
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @CodeToData
    public List<UserMessageVo> selectUserByNamePhone(String queryParam){
        return userGroupPrivateMapper.selectUserByNamePhone(queryParam);
    }
}
