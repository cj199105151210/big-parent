package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.usercenter.api.entity.FlowPosition;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class FlowPositionSqlProvider extends BaseSqlProviderUtils<FlowPosition>{

    public String insertSelective(FlowPosition record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("flow_position");
        
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

    public String updateByPrimaryKeySelective(FlowPosition record) {
        SQL sql = new SQL();
        sql.UPDATE("flow_position");
        
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

    public String selectUserByNodeId(@Param("nodeId") String nodeId){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.*,gr.guid as orgId,gr.cname as orgName ")
                .append("FROM user u ")
                .append("LEFT JOIN user_and_flow ug on u.guid = ug.user_id ")
                .append("LEFT JOIN flow_position gr on ug.org_id = gr.guid ");
        if (nodeId != null) {
            sql.append("WHERE gr.guid = '"+nodeId+"'");
        }
        return sql.toString();
    }

    public String selectUserByXM(String xm){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.guid,u.login_name,u.xm,gr.cname as orgName ")
                .append("FROM user u ")
                .append("LEFT JOIN user_and_flow ug on u.guid = ug.user_id ")
                .append("LEFT JOIN flow_position gr on ug.org_id = gr.guid ");
        if (xm != null) {
            sql.append("WHERE u.xm like '%"+xm+"%' and gr.is_delete = 0");
        }
        return sql.toString();
    }

    public  String selectFlowByUserId(String userId){
        SQL sql = new SQL();
        sql.SELECT("flow_position.guid","flow_position.cname");
        sql.FROM("flow_position","user_and_flow");
        sql.WHERE("flow_position.guid = user_and_flow.org_id AND user_id = #{userId,jdbcType=VARCHAR}");
        return  sql.toString();
    }

    public String fuzzSearch(@Param("userId")String userId,@Param("keyWord")String keyWord ){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  flow_position.guid, flow_position.cname ")
                .append("FROM flow_position,user_and_flow ")
                .append("WHERE flow_position.guid=user_and_flow.org_id AND ")
                .append(" user_and_flow.user_id = \""+userId)
                .append("\" AND flow_position.cname like '%"+keyWord+"%'");
        return sql.toString();

    }

}