package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.user.center.constant.Constant;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserGroupSqlProvider extends BaseSqlProviderUtils<UserGroup>{

    public String insertSelective(UserGroup record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_group");

        if (record.getGuid() != null) {
            sql.VALUES("guid", "#{guid,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(UserGroup record) {
        SQL sql = new SQL();
        sql.UPDATE("user_group");

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
                .append("LEFT JOIN user_and_group ug on u.guid = ug.user_id ")
                .append("LEFT JOIN user_group gr on ug.org_id = gr.guid ");
        if (nodeId != null) {
            sql.append("WHERE gr.guid = '" + nodeId + "'");
        }
        return sql.toString();
    }

    public String selectUserByTypeXM(String type, String xm) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.guid,u.login_name,u.xm,ag.org_id,t.cname FROM ");
        if (Constant.TABLE_ORGANIZATION.equals(type)) {
            sql.append("organization t ");
            sql.append("LEFT JOIN user_and_org ag on ag.org_id = t.guid ");
        } else if (Constant.TABLE_USER_GROUP.equals(type)) {
            sql.append("user_group t ");
            sql.append("LEFT JOIN user_and_group ag on ag.org_id = t.guid ");
        } else if (Constant.TABLE_FLOW_POSITION.equals(type)) {
            sql.append("flow_position t ");
            sql.append("LEFT JOIN user_and_flow ag on ag.org_id = t.guid ");
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            sql.append("user_group_private t ");
            sql.append("LEFT JOIN user_and_private_group ag on ag.org_id = t.guid ");
        } else {
            return null;
        }

        sql.append("LEFT JOIN user u on u.guid = ag.user_id ");
        sql.append("WHERE u.guid is not null and t.is_delete = 0 ");

        if (xm != null) {
            sql.append("and u.xm like '%" + xm + "%' ");
        }
        return sql.toString();
    }

    public String selectAllUsers(@Param("type") String type,@Param("nodeId") String nodeId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.guid as id,u.xm as name,ag.org_id as pId,u.login_name as loginName, ");
        sql.append("u.avator as photoUrl,u.duty as duty FROM ");
        if (Constant.TABLE_ORGANIZATION.equals(type)) {
            sql.append("organization t ");
            sql.append("LEFT JOIN user_and_org ag on ag.org_id = t.guid ");
        } else if (Constant.TABLE_USER_GROUP.equals(type)) {
            sql.append("user_group t ");
            sql.append("LEFT JOIN user_and_group ag on ag.org_id = t.guid ");
        } else if (Constant.TABLE_FLOW_POSITION.equals(type)) {
            sql.append("flow_position t ");
            sql.append("LEFT JOIN user_and_flow ag on ag.org_id = t.guid ");
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            sql.append("user_group_private t ");
            sql.append("LEFT JOIN user_and_private_group ag on ag.org_id = t.guid ");
        } else {
            return null;
        }
        sql.append("LEFT JOIN user u on u.guid = ag.user_id ");
        sql.append("WHERE u.guid is not null and t.is_delete = 0 ");

        if (nodeId != null) {
            sql.append("and ag.org_id = '"+nodeId+"' ");
        }
        sql.append("order by u.first_letter asc ");
        return sql.toString();
    }

    public String deleteFromRelation(@Param("type") String type, @Param("userAndOrg") UserAndOrg userAndOrg){
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        if (Constant.TABLE_ORGANIZATION.equals(type)) {
            sql.append("user_and_org ");
        } else if (Constant.TABLE_USER_GROUP.equals(type)) {
            sql.append("user_and_group ");
        } else if (Constant.TABLE_FLOW_POSITION.equals(type)) {
            sql.append("user_and_flow ");
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            sql.append("user_and_private_group ");
        } else {
            return null;
        }
        sql.append("where 1=1 ");
        if (userAndOrg.getUserId() != null) {
            sql.append("and user_id = '"+userAndOrg.getUserId()+"'");
        }
        if (userAndOrg.getOrgId() != null) {
            sql.append("and org_id = '"+userAndOrg.getOrgId()+"'");
        }
        return sql.toString();
    }


    public String selectGroupUserId(String userId){
        SQL sql  = new SQL();
        sql.SELECT("user_group.guid","user_group.cname");
        sql.FROM("user_group","user_and_group");
        sql.WHERE("user_group.guid = user_and_group.org_id AND user_id = #{userId,jdbcType=VARCHAR}");
        return sql.toString();
    }

    public String fuzzSearch(@Param("userId")String userId,@Param("keyWord")String keyWord ) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  user_group.guid, user_group.cname ")
                .append("FROM user_group,user_and_group ")
                .append("WHERE user_group.guid=user_and_group.org_id AND ")
                .append(" user_and_group.user_id = \"" + userId)
                .append("\" AND user_group.cname like '%" + keyWord + "%'");
        return sql.toString();
    }
    public String insertToRelation(@Param("type") String type,@Param("record") UserAndOrg record) {
        StringBuilder sql = new StringBuilder();
        StringBuilder columnSql = new StringBuilder("(");
        StringBuilder valueSql = new StringBuilder(") values (");
        if (Constant.TABLE_ORGANIZATION.equals(type)) {
            sql.append("insert into user_and_org ");
        } else if (Constant.TABLE_USER_GROUP.equals(type)) {
            sql.append("insert into user_and_group ");
        } else if (Constant.TABLE_FLOW_POSITION.equals(type)) {
            sql.append("insert into user_and_flow ");
        } else if (Constant.TABLE_PRIVATE_GROUP.equals(type)) {
            sql.append("insert into user_and_private_group ");
        } else {
            return null;
        }
        if (record.getUserId() != null) {
            columnSql.append("user_id,");
            valueSql.append("'"+record.getUserId()+"',");
        }
        if (record.getOrgId() != null) {
            columnSql.append("org_id");
            valueSql.append("'"+record.getOrgId()+"'");
        }
        if (record.getCreateTime() != null) {
            columnSql.append(",create_time");
            valueSql.append(",'"+record.getCreateTime()+"'");
        }
        if (record.getUpdateTime() != null) {
            columnSql.append(",update_time");
            valueSql.append(",'"+record.getUpdateTime()+"'");
        }
        if (record.getCreateUser() != null) {
            columnSql.append(",create_user");
            valueSql.append(",'"+record.getCreateUser()+"'");
        }
        if ("1".equals(type)) {
            if (record.getIsDirectly() != null) {
                columnSql.append(",is_directly");
                valueSql.append(",'"+record.getIsDirectly()+"'");
            }
        }
        valueSql.append(")");
        sql = sql.append(columnSql).append(valueSql);
        return sql.toString();
    }

    public String selectUserByNamePhone(@Param("queryParam") String queryParam){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.*,ug.cname as orgName,ug.guid as orgId ");
        sb.append("FROM user u ");
        sb.append("LEFT JOIN user_and_group uo ON uo.user_id = u.guid ");
        sb.append("LEFT JOIN user_group ug ON ug.guid = uo.org_id ");
        sb.append("WHERE ug.is_delete = 0 AND ug.guid is not null ");
        if (StringUtil.isNotBlank(queryParam)) {
            sb.append("AND (u.xm LIKE '%"+queryParam+"%' OR u.phone LIKE '%"+queryParam+"%' ");
            sb.append("OR u.first_letter LIKE '%"+queryParam.toUpperCase()+"%') ");
        }

        sb.append("ORDER BY u.first_letter ASC ");
        return sb.toString();
    }
}