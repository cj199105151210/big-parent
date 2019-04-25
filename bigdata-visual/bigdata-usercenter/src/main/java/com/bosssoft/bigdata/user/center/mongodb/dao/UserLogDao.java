package com.bosssoft.bigdata.user.center.mongodb.dao;

import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-20 14:37
 */
@Repository
public interface UserLogDao extends MongoRepository<UserLog,String> {
    /**
     * 根据 sid字段分页查询 --- 根据方法名的命名规范来进行方法定义查询
     * @param sid
     * @param pageable  SpringDataPageable
     * @return
     */
    public Page<UserLog> findByUserName(String sid, Pageable pageable);

    public Page<UserLog> findByUserNameAndLoginType(String userName, int loginType, Pageable pageable);

    public Page<UserLog> findByLoginType(int loginType,Pageable pageable);
}
