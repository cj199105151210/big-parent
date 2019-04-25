package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.OrganizationMapper;
import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.UserAndOrgMapper;
import com.bosssoft.bigdata.user.center.model.UserDepartment;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.common.data.annotation.CodeToData;
import com.bosssoft.bigdata.usercenter.api.entity.Organization;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationMapper orgMapper;
    @Autowired
    private UserAndOrgMapper userAndOrgMapper;


    public List<Organization> selectAllByRecord(Organization organization){
        return orgMapper.selectAllByRecord1(organization);
    }

    /**
     * 查找用户直属机构
     * @param userId
     * @return
     */
    public Organization findUserDirectOrg(String userId){
        return orgMapper.findUserDirectly(userId);
    }

    /**
     * 新建部门
     * @param organization
     */
    public int insert(Organization organization) {
        // 校验名称是否重复

        //生成32位UUID，做为用户主键
        String guid = UUID.randomUUID().toString().replace("-","");
        organization.setGuid(guid);
        organization.setIsDelete(0);
        return orgMapper.insertSelective(organization);
    }

    /**
     * 修改部门信息
     * @param organization
     * @return
     */
    public int update(Organization organization) {
        return orgMapper.updateByPrimaryKeySelective(organization);
    }

    /**
     * 删除节点（逻辑删除）
     * @param nodeId
     * @return
     */
    public int delete(String nodeId) {
        //根据主键查询到用户信息
        Organization org = orgMapper.selectByPrimaryKey(nodeId);
        //设置is_delete字段，1-删除，0-正常
        org.setIsDelete(1);
        // 删除节点下的人员信息
        List<UserMessageVo> list = orgMapper.selectUserByNodeId(nodeId);
        if (list.size() != 0) {
            userAndOrgMapper.deletePeopleByNodeId(nodeId);
        }
        return orgMapper.updateByPrimaryKeySelective(org);
    }

    /**
     * 根据ID查询人员信息
     * @param nodeId
     * @return
     */
    @CodeToData
    public List<UserMessageVo> selectUserByNodeId(String nodeId){
        return orgMapper.selectUserByNodeId(nodeId);
    }

    /**
     * 根据部门id查询部门信息
     * @param orgId
     * @return
     */
    public Organization selectOrgMessage(String orgId) {
       return orgMapper.selectByPrimaryKey(orgId);
    }

    /**
     * 根据节点删除节点下的人员信息
     *
     * @param nodeId
     * @return
     */
    public int deletePeopleByNodeId(String nodeId) {
        return userAndOrgMapper.deletePeopleByNodeId(nodeId);
    }

    /**
     * 根据姓名查询人员信息
     *
     * @param xm
     * @return
     */
    public List<UserMessage> selectUserByXM(String xm) {
        return orgMapper.selectUserByXM(xm);
    }

    /**
     * 新增用户与用户组关系数据
     * @param userAndOrg
     * @return
     */
    public int insertToRelation(UserAndOrg userAndOrg) {
        List list = userAndOrgMapper.selectRecord(userAndOrg.getUserId(),userAndOrg.getOrgId());
        if(list.isEmpty()) {
            return userAndOrgMapper.insertSelective(userAndOrg);
        }else{
            return 0;
        }
    }

    /**
     * 删除用户与用户组关系数据
     * @param userAndOrg
     * @return
     */
    public int deleteFromRelation(UserAndOrg userAndOrg) {
        return userAndOrgMapper.deleteFromRelation(userAndOrg);
    }

    /**
     * 查询用户所属部门
     * @param userId
     * @return
     */
    public List<UserDepartment> selectUserOrg(String userId) {
        return orgMapper.selectUserOrg(userId);
    }

    /**
     * 修改直属部门
     * @param userAndOrg
     */
    public int updateDirectly(UserAndOrg userAndOrg){
        String userId = userAndOrg.getUserId();
        String orgId = userAndOrg.getOrgId();
        int a = userAndOrgMapper.updateDirectlyByUserId(userId);
        int b = userAndOrgMapper.updateDirectly(userId,orgId);
        if(a==1&&b==1){
            return 1;
        }
        return 0;
    }

    /**
     * 校验名称是否重复
     * @param org
     * @param checkType 校验类型：0新增校验、1更新校验
     * @return
     */
    public int checkName(Organization org, String checkType){
        List<Organization> list = orgMapper.selectByName(org.getCname());
        if (list.isEmpty()) {
            return 0;
        } else {
            if ("0".equals(checkType)) {
                return 1;
            } else {
                for (Organization obj : list) {
                    if (!org.getGuid().equals(obj.getGuid()) && org.getCname().equals(obj.getCname())) {
                        return 1;
                    }
                }
                return 0;
            }
        }
    }

    /**
     * 关键词查询用户所在部门
     * @param userId
     * @param keyWord
     * @return
     */
    public List fuzzSearch(String userId, String keyWord) {
        return orgMapper.fuzzSearch(userId,keyWord);
    }

    /**
     * 校验编码是否重复
     * @param org
     * @param checkType 校验类型：0新增校验、1更新校验
     * @return
     */
    public int checkCode(Organization org, String checkType){
        List<Organization> list = orgMapper.selectByCode(org.getCode());
        if (list.isEmpty()) {
            return 0;
        } else {
            if ("0".equals(checkType)) {
                return 1;
            } else {
                for (Organization obj : list) {
                    if (!org.getGuid().equals(obj.getGuid()) && org.getCode().equals(obj.getCode())) {
                        return 1;
                    }
                }
                return 0;
            }
        }
    }
    /**
     * 根据姓名、姓名首字母和电话查询机构下用户信息
     * @param orgId 上级机构id
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @CodeToData
    public List<UserMessageVo> selectUserByNamePhone(String orgId, String queryParam){
        return orgMapper.selectUserByNamePhone(orgId, queryParam);
    }

}
