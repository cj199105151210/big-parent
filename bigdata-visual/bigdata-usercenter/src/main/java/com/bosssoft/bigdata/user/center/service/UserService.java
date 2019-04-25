/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: LoginService
 * Author:   Jack Ma
 * Date:     2019/3/7 9:41
 * Description: 用户登录服务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.service;


import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.OrganizationMapper;
import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.UserAndOrgMapper;
import com.bosssoft.bigdata.user.center.mapper.usercenterMapper.UserMessageMapper;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.common.data.annotation.CodeToData;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {



    @Autowired
    UserMessageMapper userMessageMapper;
    @Autowired
    OrganizationMapper organizationMapper;
    @Autowired
    UserAndOrgMapper userAndOrgMapper;

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    public int updatePassword (UserMessage user) {
        return userMessageMapper.updatePassword(user);
    }

    public int increasePasswordByLoginName(String loginName){
        return userMessageMapper.increasePasswordByLoginName(loginName);
    }

    /**
     * 通过用户名查询用户信息
     * @param loginName
     * @return
     */
    public UserMessage selectByLoginName (String loginName) {
        return userMessageMapper.selectByLoginName(loginName);
    }

    /**
     *注册用户
     * @param user
     * @return
     */
    public int insertUser(UserMessage user) {
       String guid = UUID.randomUUID().toString().replace("-","");
       user.setGuid(guid);
       return userMessageMapper.insertSelective(user);
    }

    /**
     *物理删除用户
     * @param userId
     * @return int
     */
    public int deleteById(String userId) {

        return userMessageMapper.deleteByPrimaryKey(userId);
    }

    /**
     *逻辑删除用户
     * @param guids
     * @return int
     */
    public int delete(String guids) {
        String[] arr = guids.split(",");
        for (String guid: arr) {
            UserMessage user = userMessageMapper.selectByPrimaryKey(guid);
            //1为删除，0为正常
            user.setIsDelete(1);
           int code = userMessageMapper.updateByPrimaryKeySelective(user);
           if(code == 0) {
               return 0;
           }

        }
        return 1;

    }

    /**
     *修改用户信息
     * @param user
     * @return int
     */
    public int updateUser(UserMessage user) { return userMessageMapper.updateByPrimaryKeySelective(user); }



    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public UserMessage selectUserMessage(String userId) {
        return userMessageMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据用户登录名查询用户信息
     * @param loginName
     * @return
     */
    public  UserMessage selectUser(String loginName){return userMessageMapper.selectByLoginName(loginName); }
    /**
     * 重新启用用户
     * @param guid
     * @return
     */
    public int restart(String guid) {
        UserMessage user = userMessageMapper.selectByPrimaryKey(guid);
        //1为删除，0为正常
        user.setIsDelete(0);
        return userMessageMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据类型用户名分页查询用户信息
     *
     * @param type 类型：1用户、2岗位、3机构
     * @param name 根据类型分别为用户名、岗位名和机构名
     * @return
     */
    public List<UserMessage>  selectUserForPage(String type, String name){
        return userMessageMapper.selectUserForPage(type, name);
    }

    /**
     * 根据姓名、姓名首字母和电话查询用户信息
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @CodeToData
    public List<UserMessageVo> selectUserByNamePhone(String queryParam){
        return userMessageMapper.selectUserByNamePhone(queryParam);
    }

    @CodeToData
    public List<UserMessageVo> selectUserByName(String xm){
        return userMessageMapper.selectUserByName(xm);
    }

    /**
     * 根据用户名修改用户首次登陆信息
     * @param userName
     * @return
     */
    public int updUserFirstLogin(String userName){
        if (StringUtil.isNotBlank(userName)) {
            return userMessageMapper.updUserFirstLogin(userName);
        }
        return 0;
    }

    /**
     * 通过用户名查询用户信息
     * @param name
     * @return
     */
    @CodeToData
    public UserMessageVo selectByUserName (String name) {
        return userMessageMapper.selectByUserName(name);
    }
}
