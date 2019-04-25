package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.user.center.constant.Constant;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserMessageSqlProvider {

    public String insertSelective(UserMessage record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");

        if (record.getGuid() != null) {
            sql.VALUES("guid", "#{guid,jdbcType=VARCHAR}");
        }

        if (record.getLoginName() != null) {
            sql.VALUES("login_name", "#{loginName,jdbcType=VARCHAR}");
        }

        if (record.getCardType() != null) {
            sql.VALUES("card_type", "#{cardType,jdbcType=INTEGER}");
        }

        if (record.getIdCard() != null) {
            sql.VALUES("id_card", "#{idCard,jdbcType=VARCHAR}");
        }

        if (record.getXm() != null) {
            sql.VALUES("xm", "#{xm,jdbcType=VARCHAR}");
        }

        if (record.getXb() != null) {
            sql.VALUES("xb", "#{xb,jdbcType=INTEGER}");
        }

        if (record.getAge() != null) {
            sql.VALUES("age", "#{age,jdbcType=INTEGER}");
        }

        if (record.getUserNo() != null) {
            sql.VALUES("user_no", "#{userNo,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }

        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }

        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }

        if (record.getMail() != null) {
            sql.VALUES("mail", "#{mail,jdbcType=VARCHAR}");
        }

        if (record.getAvator() != null) {
            sql.VALUES("avator", "#{avator,jdbcType=VARCHAR}");
        }

        if (record.getDuty() != null) {
            sql.VALUES("duty", "#{duty,jdbcType=INTEGER}");
        }

        if (record.getSortNo() != null) {
            sql.VALUES("sort_no", "#{sortNo,jdbcType=INTEGER}");
        }

        if (record.getErrNo() != null) {
            sql.VALUES("err_no", "#{errNo,jdbcType=INTEGER}");
        }

        if (record.getThirdName() != null) {
            sql.VALUES("third_name", "#{thirdName,jdbcType=VARCHAR}");
        }

        if (record.getQqOpenid() != null) {
            sql.VALUES("qq_openid", "#{qqOpenid,jdbcType=VARCHAR}");
        }

        if (record.getWxOpenid() != null) {
            sql.VALUES("wx_openid", "#{wxOpenid,jdbcType=VARCHAR}");
        }

        if (record.getIsDelete() != null) {
            sql.VALUES("is_delete", "#{isDelete,jdbcType=INTEGER}");
        }

        if (record.getIsAuth() != null) {
            sql.VALUES("is_auth", "#{isAuth,jdbcType=INTEGER}");
        }

        if (record.getExtend() != null) {
            sql.VALUES("extend", "#{extend,jdbcType=VARCHAR}");
        }

        if (record.getFirstLogin() != null) {
            sql.VALUES("first_login", "#{firstLogin,jdbcType=INTEGER}");
        }

        if (record.getFirstLetter() != null) {
            sql.VALUES("first_letter", "#{firstLetter,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserMessage record) {
        SQL sql = new SQL();
        sql.UPDATE("user");

        if (record.getLoginName() != null) {
            sql.SET("login_name = #{loginName,jdbcType=VARCHAR}");
        }

        if (record.getCardType() != null) {
            sql.SET("card_type = #{cardType,jdbcType=INTEGER}");
        }

        if (record.getIdCard() != null) {
            sql.SET("id_card = #{idCard,jdbcType=VARCHAR}");
        }

        if (record.getXm() != null) {
            sql.SET("xm = #{xm,jdbcType=VARCHAR}");
        }

        if (record.getXb() != null) {
            sql.SET("xb = #{xb,jdbcType=INTEGER}");
        }

        if (record.getAge() != null) {
            sql.SET("age = #{age,jdbcType=INTEGER}");
        }

        if (record.getUserNo() != null) {
            sql.SET("user_no = #{userNo,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }

        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }

        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }

        if (record.getMail() != null) {
            sql.SET("mail = #{mail,jdbcType=VARCHAR}");
        }

        if (record.getAvator() != null) {
            sql.SET("avator = #{avator,jdbcType=VARCHAR}");
        }

        if (record.getDuty() != null) {
            sql.SET("duty = #{duty,jdbcType=INTEGER}");
        }

        if (record.getSortNo() != null) {
            sql.SET("sort_no = #{sortNo,jdbcType=INTEGER}");
        }

        if (record.getErrNo() != null) {
            sql.SET("err_no = #{errNo,jdbcType=INTEGER}");
        }

        if (record.getThirdName() != null) {
            sql.SET("third_name = #{thirdName,jdbcType=VARCHAR}");
        }

        if (record.getQqOpenid() != null) {
            sql.SET("qq_openid = #{qqOpenid,jdbcType=VARCHAR}");
        }

        if (record.getWxOpenid() != null) {
            sql.SET("wx_openid = #{wxOpenid,jdbcType=VARCHAR}");
        }

        if (record.getIsDelete() != null) {
            sql.SET("is_delete = #{isDelete,jdbcType=INTEGER}");
        }

        if (record.getIsAuth() != null) {
            sql.SET("is_auth = #{isAuth,jdbcType=INTEGER}");
        }

        if (record.getExtend() != null) {
            sql.SET("extend = #{extend,jdbcType=VARCHAR}");
        }

        if (record.getFirstLogin() != null) {
            sql.SET("first_login = #{firstLogin,jdbcType=INTEGER}");
        }

        if (record.getFirstLetter() != null) {
            sql.SET("first_letter = #{firstLetter,jdbcType=VARCHAR}");
        }

        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }

        sql.WHERE("guid = #{guid,jdbcType=VARCHAR}");

        return sql.toString();
    }

    public String increasePasswordByLoginName(String loginName) {
        StringBuffer sql = new StringBuffer();

        sql.append("update user set err_no=err_no+1 where login_name=#{loginName}");

        return sql.toString();

    }

    public String selectUserForPage(@Param("type") String type, @Param("name") String name) {
        StringBuilder sql = new StringBuilder();
        if (Constant.USER_USER.equals(type)) {
            sql.append("SELECT u.* ");
            sql.append("FROM user u WHERE 1=1 ");
            if (StringUtil.isNotBlank(name)) {
                sql.append("AND u.xm LIKE '%" + name + "%' ");
            }
        } else if (Constant.POSITION_USER.equals(type)) {
            sql.append("SELECT DISTINCT u.guid, u.login_name, u.xm, u.user_no,u.type, u.phone, u.mail, ");
            sql.append("u.duty, u.is_delete FROM flow_position fp ");
            sql.append("LEFT JOIN user_and_flow uf ON fp.guid = uf.org_id ");
            sql.append("LEFT JOIN user u ON uf.user_id = u.guid ");
            sql.append("WHERE fp.is_delete = 0 AND u.guid is not null ");
            if (StringUtil.isNotBlank(name)) {
                sql.append("AND fp.cname LIKE '%" + name + "%' ");
            }
        } else if (Constant.ORGANIZATION_USER.equals(type)) {
            sql.append("SELECT DISTINCT u.guid, u.login_name, u.xm, u.user_no,u.type, u.phone, u.mail, ");
            sql.append("u.duty, u.is_delete FROM organization org ");
            sql.append("LEFT JOIN user_and_org uo ON org.guid = uo.org_id ");
            sql.append("LEFT JOIN user u ON uo.user_id = u.guid ");
            sql.append("WHERE org.is_delete = 0 and u.guid is not null ");
            if (StringUtil.isNotBlank(name)) {
                sql.append("AND org.cname LIKE '%" + name + "%' ");
            }
        }
        sql.append("ORDER BY u.first_letter ASC ");
        return sql.toString();
    }

    public String selectUserByNamePhone(@Param("queryParam") String queryParam){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.*,org.cname as orgName,org.guid as orgId ");
        sb.append("FROM user u ");
        sb.append("LEFT JOIN user_and_org uo ON uo.user_id = u.guid ");
        sb.append("LEFT JOIN organization org ON org.guid = uo.org_id ");
        sb.append("WHERE org.is_delete = 0 AND org.guid is not null ");
        if (StringUtil.isNotBlank(queryParam)) {
            sb.append("AND (u.xm LIKE '%"+queryParam+"%' OR u.phone LIKE '%"+queryParam+"%' ");
            sb.append("OR u.first_letter LIKE '%"+queryParam.toUpperCase()+"%') ");
        }
        sb.append("ORDER BY u.first_letter ASC ");
        return sb.toString();
    }

    public String selectUserByName(@Param("xm") String xm){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.*,org.cname as orgName,org.guid as orgId ");
        sb.append("FROM user u ");
        sb.append("LEFT JOIN user_and_org uo ON uo.user_id = u.guid and uo.is_directly = 1 ");
        sb.append("LEFT JOIN organization org ON org.guid = uo.org_id ");
        sb.append("WHERE 1 = 1 ");
        if (StringUtil.isNotBlank(xm)) {
            sb.append("AND (u.xm LIKE '%"+xm+"%' ");
            sb.append("OR u.first_letter LIKE '%"+xm.toUpperCase()+"%') ");
        }
        sb.append("ORDER BY u.first_letter ASC ");
        return sb.toString();
    }


}