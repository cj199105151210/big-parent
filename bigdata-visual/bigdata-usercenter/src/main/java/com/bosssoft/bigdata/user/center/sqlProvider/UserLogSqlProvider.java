package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.usercenter.api.entity.UserLog;
import org.apache.ibatis.jdbc.SQL;

public class UserLogSqlProvider {

    public String insertSelective(UserLog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_log");
        
        if (record.getGuid() != null) {
            sql.VALUES("guid", "#{guid,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginType() != null) {
            sql.VALUES("login_type", "#{loginType,jdbcType=INTEGER}");
        }
        
        if (record.getOptTime() != null) {
            sql.VALUES("opt_time", "#{optTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOptType() != null) {
            sql.VALUES("opt_type", "#{optType,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserLog record) {
        SQL sql = new SQL();
        sql.UPDATE("user_log");
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginType() != null) {
            sql.SET("login_type = #{loginType,jdbcType=INTEGER}");
        }
        
        if (record.getOptTime() != null) {
            sql.SET("opt_time = #{optTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOptType() != null) {
            sql.SET("opt_type = #{optType,jdbcType=INTEGER}");
        }
        
        sql.WHERE("guid = #{guid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}