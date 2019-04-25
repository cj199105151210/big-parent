package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.usercenter.api.entity.Organization;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class OrganizationSqlProvider extends BaseSqlProviderUtils<Organization>{

    public String insertSelective(Organization record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("organization");

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

        if (record.getSortNo() != null) {
            sql.VALUES("sort_no", "#{sortNo,jdbcType=INTEGER}");
        }

        if (record.getIsDelete() != null) {
            sql.VALUES("is_delete", "#{isDelete,jdbcType=INTEGER}");
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

        if (record.getCreateUserName() != null) {
            sql.VALUES("create_user_name", "#{createUserName,jdbcType=VARCHAR}");
        }

        if (record.getExtend() != null) {
            sql.VALUES("extend", "#{extend,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }

        if (record.getOrgType() != null) {
            sql.VALUES("org_type", "#{orgType,jdbcType=VARCHAR}");
        }

        if (record.getIsEffective() != null) {
            sql.VALUES("is_effective", "#{isEffective,jdbcType=VARCHAR}");
        }

        if (record.getIsEntity() != null) {
            sql.VALUES("is_entity", "#{isEntity,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Organization record) {
        SQL sql = new SQL();
        sql.UPDATE("organization");

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

        if (record.getSortNo() != null) {
            sql.SET("sort_no = #{sortNo,jdbcType=INTEGER}");
        }

        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{isDelete,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
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

        if (record.getOrgType() != null) {
            sql.SET("org_type = #{orgType,jdbcType=VARCHAR}");
        }

        if (record.getIsEffective() != null) {
            sql.SET("is_effective = #{isEffective,jdbcType=VARCHAR}");
        }

        if (record.getIsEntity() != null) {
            sql.SET("is_entity = #{isEntity,jdbcType=VARCHAR}");
        }

        sql.WHERE("guid = #{guid,jdbcType=VARCHAR}");

        return sql.toString();
    }

    public String selectByParentId(String parentId) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("organization");
        sql.WHERE("parent_id = #{parentId,jdbcType=VARCHAR }");
        return sql.toString();
    }

    public String selectUserOrg(String userId) {
        SQL sql = new SQL();
        sql.SELECT("organization.guid", "organization.cname", "user_and_org.is_directly");
        sql.FROM("organization", "user_and_org");
        sql.WHERE("organization.guid=user_and_org.org_id and user_id=#{userId,jdbcType=VARCHAR}");
        return sql.toString();
    }

    public String selectUserByNodeId(@Param("nodeId") String nodeId){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.*,gr.guid as orgId,gr.cname as orgName ")
                .append("FROM user u ")
                .append("LEFT JOIN user_and_org ug on u.guid = ug.user_id ")
                .append("LEFT JOIN organization gr on ug.org_id = gr.guid ")
                .append("WHERE gr.is_delete = 0 ");
        if (nodeId != null) {
            sql.append("AND gr.guid = '"+nodeId+"'");
        }
        sql.append("ORDER BY u.first_letter asc");
        return sql.toString();
    }

    public String selectUserByXM(String xm){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.guid,u.login_name,u.xm,gr.cname as orgName ")
                .append("FROM user u ")
                .append("LEFT JOIN user_and_org ug on u.guid = ug.user_id ")
                .append("LEFT JOIN organization gr on ug.org_id = gr.guid ");
        if (xm != null) {
            sql.append("WHERE u.xm like '%"+xm+"%' and gr.is_delete = 0");
        }
        return sql.toString();
    }

    public String selectOrganizationByNodeId(@Param("nodeId") String nodeId){
        StringBuilder sql = new StringBuilder();
        sql.append("select guid, cname, parent_id ")
                .append("from organization ")
                .append("WHERE is_delete = 0 ");
        if (nodeId != null ) {
            sql.append("and parent_id = '"+nodeId+"'");
        }
        return sql.toString();
    }

    public String fuzzSearch(@Param("userId")String userId,@Param("keyWord")String keyWord ){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  organization.guid, organization.cname, user_and_org.is_directly ")
                .append("FROM organization,user_and_org ")
                .append("WHERE organization.guid=user_and_org.org_id AND ")
                .append(" user_and_org.user_id = \""+userId)
                .append("\" AND organization.cname like '%"+keyWord+"%'");
        return sql.toString();

    }

    public String selectUserByNamePhone(@Param("orgId") String orgId, @Param("queryParam") String queryParam){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.*,org.cname as orgName,org.guid as orgId ");
        sb.append("FROM user u ");
        sb.append("LEFT JOIN user_and_org uo ON uo.user_id = u.guid ");
        sb.append("LEFT JOIN organization org ON org.guid = uo.org_id ");
        sb.append("WHERE org.is_delete = 0 AND org.guid is not null ");
        if (StringUtil.isNotBlank(orgId)) {
            sb.append("AND org.guid = '"+orgId+"' ");
        }
        if (StringUtil.isNotBlank(queryParam)) {
            sb.append("AND (u.xm LIKE '%"+queryParam+"%' OR u.phone LIKE '%"+queryParam+"%' ");
            sb.append("OR u.first_letter LIKE '%"+queryParam.toUpperCase()+"%') ");
        }

        sb.append("ORDER BY u.first_letter ASC ");
        return sb.toString();
    }

}
