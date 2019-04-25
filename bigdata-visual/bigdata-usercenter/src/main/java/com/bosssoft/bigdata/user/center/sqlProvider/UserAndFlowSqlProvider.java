package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.usercenter.api.entity.UserAndFlow;
import org.apache.ibatis.jdbc.SQL;

public class UserAndFlowSqlProvider {

    public String insertSelective(UserAndFlow record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_and_flow");
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgId() != null) {
            sql.VALUES("org_id", "#{orgId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateUser() != null) {
            sql.VALUES("create_user", "#{createUser,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }
}