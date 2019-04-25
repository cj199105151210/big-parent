package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserAndOrgSqlProvider {

    public String insertSelective(UserAndOrg record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_and_org");
        
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
            sql.VALUES("create_user", "#{createUser,jdbcType=VARCHAR}");
        }

        if(record.getIsDirectly()!=null){
            sql.VALUES("is_directly", "#{isDirectly,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByOrgId(String orgId){
        SQL sql = new SQL();
        sql.SELECT("user_id");
        sql.FROM("user_and_org");
        sql.WHERE("org_id = #{orgId,jdbcType=VARCHAR }");
        return sql.toString();
    }

    public String updateDirectlyByUserId(String userId){
        SQL sql = new SQL();
        sql.UPDATE("user_and_org");
        sql.SET("is_directly='0'");
        sql.WHERE("user_id = #{userId,jdbcType=VARCHAR} AND is_directly='1'");

        return sql.toString();
    }

    public String updateDirectly(@Param("userId") String userId, @Param("orgId")String orgId){
        SQL sql = new SQL();
        sql.UPDATE("user_and_org");
        sql.SET("is_directly='1'");
        sql.WHERE("user_id = #{userId,jdbcType=VARCHAR} and org_id = #{orgId,jdbcType=VARCHAR}");

         return sql.toString();
    }

   public String selectRecord(@Param("userId") String userId, @Param("orgId") String orgId){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("user_and_org");
        sql.WHERE("user_id = #{userId,jdbcType=VARCHAR} and org_id = #{orgId,jdbcType=VARCHAR}");

        return sql.toString();

   }
}