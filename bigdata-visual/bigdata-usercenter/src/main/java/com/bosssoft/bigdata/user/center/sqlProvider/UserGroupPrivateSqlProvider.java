package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroupPrivate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserGroupPrivateSqlProvider {

    public String insertSelective(UserGroupPrivate record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_group_private");

        if (record.getGuid() != null) {
            sql.VALUES("guid", "#{guid,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getCname() != null) {
            sql.VALUES("cname", "#{cname,jdbcType=VARCHAR}");
        }

        if (record.getShortName() != null) {
            sql.VALUES("short_name", "#{shortName,jdbcType=VARCHAR}");
        }

        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }

        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=VARCHAR}");
        }

        if (record.getCreateId() != null) {
            sql.VALUES("create_id", "#{createId,jdbcType=VARCHAR}");
        }

        if (record.getSortNo() != null) {
            sql.VALUES("sort_no", "#{sortNo,jdbcType=INTEGER}");
        }

        if (record.getIsDelete() != null) {
            sql.VALUES("is_delete", "#{isDelete,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTme() != null) {
            sql.VALUES("update_tme", "#{updateTme,jdbcType=TIMESTAMP}");
        }

        if (record.getCreateUser() != null) {
            sql.VALUES("create_user", "#{createUser,jdbcType=VARCHAR}");
        }

        if (record.getCreateUserName() != null) {
            sql.VALUES("create_user_name", "#{createUserName,jdbcType=VARCHAR}");
        }

        if (record.getExtend() != null) {
            sql.VALUES("extend", "#{extend,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserGroupPrivate record) {
        SQL sql = new SQL();
        sql.UPDATE("user_group_private");

        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getCname() != null) {
            sql.SET("cname = #{cname,jdbcType=VARCHAR}");
        }

        if (record.getShortName() != null) {
            sql.SET("short_name = #{shortName,jdbcType=VARCHAR}");
        }

        if (record.getCode() != null) {
            sql.SET("code = #{code,jdbcType=VARCHAR}");
        }

        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=VARCHAR}");
        }

        if (record.getCreateId() != null) {
            sql.SET("create_id = #{createId,jdbcType=VARCHAR}");
        }

        if (record.getSortNo() != null) {
            sql.SET("sort_no = #{sortNo,jdbcType=INTEGER}");
        }

        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{isDelete,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTme() != null) {
            sql.SET("update_tme = #{updateTme,jdbcType=TIMESTAMP}");
        }

        if (record.getCreateUser() != null) {
            sql.SET("create_user = #{createUser,jdbcType=VARCHAR}");
        }

        if (record.getCreateUserName() != null) {
            sql.SET("create_user_name = #{createUserName,jdbcType=VARCHAR}");
        }

        if (record.getExtend() != null) {
            sql.SET("extend = #{extend,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }

        sql.WHERE("guid = #{guid,jdbcType=VARCHAR}");

        return sql.toString();
    }

    public String selectUserByNodeId(@Param("nodeId") String nodeId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.*,gr.guid as orgId,gr.cname as orgName ")
                .append("FROM user u ")
                .append("LEFT JOIN user_and_private_group ug on u.guid = ug.user_id ")
                .append("LEFT JOIN user_group_private gr on ug.org_id = gr.guid ");
        if (nodeId != null) {
            sql.append("WHERE gr.guid = '" + nodeId + "'");
        }
        return sql.toString();
    }

    public String selectUserByXM(String xm) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.guid,u.login_name,u.xm,gr.cname as orgName ")
                .append("FROM user u ")
                .append("LEFT JOIN user_and_private_group ug on u.guid = ug.user_id ")
                .append("LEFT JOIN user_group_private gr on ug.org_id = gr.guid ");
        if (xm != null) {
            sql.append("WHERE u.xm like '%" + xm + "%' and gr.is_delete = 0");
        }
        return sql.toString();
    }

    public String selectUserByNamePhone(@Param("queryParam") String queryParam){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.*,ug.cname as orgName,ug.guid as orgId ");
        sb.append("FROM user u ");
        sb.append("LEFT JOIN user_and_private_group uo ON uo.user_id = u.guid ");
        sb.append("LEFT JOIN user_group_private ug ON ug.guid = uo.org_id ");
        sb.append("WHERE ug.is_delete = 0 AND ug.guid is not null ");
        if (StringUtil.isNotBlank(queryParam)) {
            sb.append("AND (u.xm LIKE '%"+queryParam+"%' OR u.phone LIKE '%"+queryParam+"%' ");
            sb.append("OR u.first_letter LIKE '%"+queryParam.toUpperCase()+"%') ");
        }

        sb.append("ORDER BY u.first_letter ASC ");
        return sb.toString();
    }

    public String selectAllUserGroupPrivate(String userId){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT guid, cname, code, parent_id ");
        sb.append("FROM user_group_private ");
        sb.append("WHERE is_delete = 0 ");
        if (StringUtil.isNotBlank(userId)) {
            sb.append("AND  create_id =  '"+userId+"' ");
        }
        return sb.toString();
    }

}