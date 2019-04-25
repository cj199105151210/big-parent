/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: UserGroupService
 * Author:   Jack Ma
 * Date:     2019/3/11 15:30
 * Description: 用户组管理服务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.constant.Constant;
import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.*;
import com.bosssoft.bigdata.user.center.model.RelationParam;
import com.bosssoft.bigdata.user.center.model.Tree;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.common.data.annotation.CodeToData;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndGroup;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserGroupService{

    @Autowired
    UserGroupMapper userGroupMapper;
    @Autowired
    UserAndGroupMapper userAndGroupMapper;
    @Autowired
    OrganizationMapper organizationMapper;
    @Autowired
    FlowPositionMapper flowPositionMapper;
    @Autowired
    UserGroupPrivateMapper userGroupPrivateMapper;
    @Autowired
    UserAndOrgMapper userAndOrgMapper;

    /**
     * 查找树状列表数据
     *
     * @return
     */
    public List<UserGroup> selectUserGroupByParentId(String parentId) {
        return userGroupMapper.selectUserGroupByParentId(parentId);
    }

    /**
     * 获取全部用户组
     * @param userGroup
     * @return
     */
    public List<UserGroup> selectAllUserGroup(UserGroup userGroup){
        return userGroupMapper.getAllByRecord(userGroup);
    }

    /**
     * 根据id查询用户组信息
     *
     * @param id
     * @return
     */
    public UserGroup selectByPrimaryKey(String id) {
        return userGroupMapper.selectByPrimaryKey(id);
    }

    /**
     * 校验名称是否重复
     * @param name
     * @return
     */
    public int checkName(String name){
        List<UserGroup> list = userGroupMapper.selectByName(name);
        if (list.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }
    /**
     * 新增节点
     *
     * @param userGroup
     * @return
     */
    public int insertNode(UserGroup userGroup) {
        String id = UUID.randomUUID().toString().replace("-", "");
        userGroup.setGuid(id);
        userGroup.setIsDelete(0);
        return userGroupMapper.insertSelective(userGroup);
    }

    /**
     * 更新节点
     *
     * @param userGroup
     * @return
     */
    public int updateNode(UserGroup userGroup) {
        return userGroupMapper.updateByPrimaryKeySelective(userGroup);
    }

    /**
     * 根据ID删除节点（逻辑删除）
     *
     * @param nodeId
     * @return
     */
    public int deleteNodeById(String nodeId) {
        // 根据Id查询信息
        UserGroup userGroup = userGroupMapper.selectByPrimaryKey(nodeId);
        userGroup.setIsDelete(1);
        // 删除节点下的人员信息
        List<UserMessageVo> list = userGroupMapper.selectUserByNodeId(nodeId);
        if (list.size() != 0) {
            userAndGroupMapper.deletePeopleByNodeId(nodeId);
        }
        return userGroupMapper.updateByPrimaryKeySelective(userGroup);
    }

    /**
     * 根据ID查询人员信息
     *
     * @param nodeId
     * @return
     */
    public List<UserMessageVo> selectUserByNodeId(String nodeId) {
        if (nodeId == null) {
            return Collections.EMPTY_LIST;
        }
        return userGroupMapper.selectUserByNodeId(nodeId);
    }

    /**
     * 根据姓名查询人员信息
     *
     * @param xm
     * @return
     */
    public List<UserMessageVo> selectUserByTypeXM(String type, String xm) {
        return userGroupMapper.selectUserByTypeXM(type, xm);
    }

    /**
     * 根据节点删除节点下的人员信息
     *
     * @param nodeId
     * @return
     */
    public int deletePeopleByNodeId(String nodeId) {
        return userAndGroupMapper.deletePeopleByNodeId(nodeId);
    }

    /**
     * 根据type查询所有表数据
     *
     * @param type 业务类型
     * @param userId 登录用户id：查询个人组时使用
     * @return
     */
    public List<Tree> selectAllTreeData(String type,String userId) {
        List<Tree> list = new ArrayList<>();
        if (Constant.TABLE_ORGANIZATION.equals(type)) {
            list = organizationMapper.selectAllOrganization();
        } else if (Constant.TABLE_USER_GROUP.equals(type)) {
            list = userGroupMapper.selectAllUserGroup();
        } else if (Constant.TABLE_FLOW_POSITION.equals(type)) {
            list = flowPositionMapper.selectAllFlowPosition();
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            list = userGroupPrivateMapper.selectAllUserGroupPrivate(userId);
        }
        return list;
    }

    /**
     * 查询关联的所有人员信息
     *
     * @param type 业务类型
     * @param nodeId 节点Id
     * @return
     */
    @CodeToData
    public List<Tree> selectAllUsers(String type,String nodeId) {
        if (type == null || type == "") {
            return Collections.EMPTY_LIST;
        }
        return userGroupMapper.selectAllUsers(type,nodeId);
    }

    /**
     * 新增用户与用户组关系数据
     * @param userAndGroup
     * @return
     */
    public int insertToRelation(UserAndGroup userAndGroup) {
        // 查询是否已经存在该数据
        UserAndGroup group = userAndGroupMapper.selectUserRelation(userAndGroup);
        if ( group == null ) {
            return userAndGroupMapper.insertSelective(userAndGroup);
        } else {
            return 0;
        }
    }

    /**
     * 删除用户与用户组关系数据
     * @param userAndGroup
     * @return
     */
    public int deleteFromRelation(UserAndGroup userAndGroup) {
        return userAndGroupMapper.deleteFromRelation(userAndGroup);
    }

    /**
     * 根据节点Id查询节点数据
     *
     * @param type 表类型：1机构管理、2用户组管理、3流程岗位管理、4用户私组表
     * @param nodeId 父节点id
     * @return
     */
    public List<Tree> selectTreeDataByNodeId(String type,String nodeId) {
        List<Tree> list = new ArrayList<>();
        if (Constant.TABLE_ORGANIZATION.equals(type)) {
            list = organizationMapper.selectOrganizationByNodeId(nodeId);
        } else if (Constant.TABLE_USER_GROUP.equals(type)) {
            list = userGroupMapper.selectUserGroupByNodeId(nodeId);
        } else if (Constant.TABLE_FLOW_POSITION.equals(type)) {
            list = flowPositionMapper.selectFlowPositionByNodeId(nodeId);
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            list = userGroupPrivateMapper.selectUserGroupPrivateByNodeId(nodeId);
        }
        return list;
    }


    /**
     * 根据用户id查询所属用户组
     * @param userId
     * @return
     */
    public List selectUserGroup(String userId) {
        return userGroupMapper.selectGroupByUserId(userId);
    }

    /**
     * 关键词查询用户所在部门
     * @param userId
     * @param keyWord
     * @return
     */
    public List fuzzSearch(String userId, String keyWord) {
        return userGroupMapper.fuzzSearch(userId,keyWord);
    }

    /**
     * 调整人员与机构、用户组、流程岗位和个人组之间的关系
     * @param param 参数实体类
     * @param userAndOrg 用户和机构关系类
     * @return
     */
    public int adjustRelation(RelationParam param,UserAndOrg userAndOrg){
        int result;
        if ("0".equals(param.getAdjustType())) {
            // 将树中人员与机构关系删除
            userGroupMapper.deleteFromRelation(param.getType(), userAndOrg);
            // 新增人员与机构关系数据
            userAndOrg.setOrgId(param.getTargetId());
            result = userGroupMapper.insertToRelation(param.getTargetType(), userAndOrg);
        } else {
            // 删除人员与机构关系数据
            userGroupMapper.deleteFromRelation(param.getType(), userAndOrg);
            // 新增树中人员与机构关系
            userAndOrg.setOrgId(param.getTargetId());
            result = userGroupMapper.insertToRelation(param.getTargetType(), userAndOrg);
        }
        return result;
    }

    /**
     * 根据ID查询人员信息
     *
     * @param nodeId 父节点id
     * @param type 表类型：1机构管理、2用户组管理、3流程岗位管理、4用户私组表
     * @return
     */
    public List<UserMessageVo> selectUserByParentId(String type, String nodeId) {
        if (nodeId == null) {
            return Collections.EMPTY_LIST;
        }
        List<UserMessageVo> list = new ArrayList<>();
        if (Constant.TABLE_ORGANIZATION.equals(type)) {
            list = organizationMapper.selectUserByNodeId(nodeId);
        } else if (Constant.TABLE_USER_GROUP.equals(type)) {
            list = userGroupMapper.selectUserByNodeId(nodeId);
        } else if (Constant.TABLE_FLOW_POSITION.equals(type)) {
            list = flowPositionMapper.selectUserByNodeId(nodeId);
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            list = userGroupPrivateMapper.selectUserByNodeId(nodeId);
        }
        return list;
    }

    /**
     * 获取全部用户组信息
     * @return
     */
    public List<UserGroup> selectAllUserGroupList(){
        return userGroupMapper.selectAllUserGroupList();
    }

    /**
     * 根据姓名、姓名首字母和电话查询用户组下用户信息
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @CodeToData
    public List<UserMessageVo> selectUserByNamePhone(String queryParam){
        return userGroupMapper.selectUserByNamePhone(queryParam);
    }

    /**
     * 根据类型查询用户组或个人组信息
     *
     * @param type 业务类型
     * @return
     */
    public List<UserMessageVo> selectUserInfoByType(String type) {
        List<UserMessageVo> list = new ArrayList<>();
        if (Constant.TABLE_USER_GROUP.equals(type)) {
            list = userGroupMapper.selectUserByNamePhone(null);
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            list = userGroupPrivateMapper.selectUserByNamePhone(null);
        }
        return list;
    }
}
