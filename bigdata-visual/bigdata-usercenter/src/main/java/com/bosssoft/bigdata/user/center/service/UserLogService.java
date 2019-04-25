package com.bosssoft.bigdata.user.center.service;

import com.bosssoft.bigdata.user.center.mongodb.dao.UserLogDao;
import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author fanghuaizheng
 * @Description:用户日志业务层
 * @date 2019-03-14 10:38
 */
@Service
public class UserLogService {

    @Autowired
    UserLogDao userLogDao;

    /**
     * 增加用户日志
     * @param userLog
     * @return
     */
    public UserLog insertUserLog(UserLog userLog){
        return userLogDao.insert(userLog);
    }

    /**
     * 登录方式分页查询
     * @param type
     * @param pageable
     * @return
     */
    public Page<UserLog> findByLoginType(int type, Pageable pageable){ return userLogDao.findByLoginType(type,pageable);}

    /**
     * 登录名分页查询
     * @param userName
     * @param pageable
     * @return
     */
    public Page<UserLog> findByUserName(String userName,Pageable pageable){ return userLogDao.findByUserName(userName,pageable);}

    /**
     * 无条件分页查询
     * @param pageRequest
     * @return
     */
    public Page<UserLog> findAll(PageRequest pageRequest){ return userLogDao.findAll(pageRequest);}

    public Page<UserLog> findByUserNameAndLoginType(String userName,int type ,Pageable pageable) {
       return userLogDao.findByUserNameAndLoginType(userName, type, pageable);
    }

}
