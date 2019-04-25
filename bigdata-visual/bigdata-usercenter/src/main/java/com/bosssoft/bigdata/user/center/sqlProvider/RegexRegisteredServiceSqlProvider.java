package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.usercenter.api.entity.RegexRegisteredServiceWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class RegexRegisteredServiceSqlProvider {

    public String insertSelective(RegexRegisteredServiceWithBLOBs record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("regexregisteredservice");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getExpressionType() != null) {
            sql.VALUES("expression_type", "#{expressionType,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getEvaluationOrder() != null) {
            sql.VALUES("evaluation_order", "#{evaluationOrder,jdbcType=INTEGER}");
        }
        
        if (record.getInformationurl() != null) {
            sql.VALUES("informationUrl", "#{informationurl,jdbcType=VARCHAR}");
        }
        
        if (record.getLogo() != null) {
            sql.VALUES("logo", "#{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getLogoutType() != null) {
            sql.VALUES("logout_type", "#{logoutType,jdbcType=INTEGER}");
        }
        
        if (record.getLogoutUrl() != null) {
            sql.VALUES("logout_url", "#{logoutUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPrivacyurl() != null) {
            sql.VALUES("privacyUrl", "#{privacyurl,jdbcType=VARCHAR}");
        }
        
        if (record.getServiceid() != null) {
            sql.VALUES("serviceId", "#{serviceid,jdbcType=VARCHAR}");
        }
        
        if (record.getTheme() != null) {
            sql.VALUES("theme", "#{theme,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessStrategy() != null) {
            sql.VALUES("access_strategy", "#{accessStrategy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getAttributeRelease() != null) {
            sql.VALUES("attribute_release", "#{attributeRelease,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getExpirationPolicy() != null) {
            sql.VALUES("expiration_policy", "#{expirationPolicy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getMfaPolicy() != null) {
            sql.VALUES("mfa_policy", "#{mfaPolicy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getProxyPolicy() != null) {
            sql.VALUES("proxy_policy", "#{proxyPolicy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getPublicKey() != null) {
            sql.VALUES("public_key", "#{publicKey,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getRequiredHandlers() != null) {
            sql.VALUES("required_handlers", "#{requiredHandlers,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getUsernameAttr() != null) {
            sql.VALUES("username_attr", "#{usernameAttr,jdbcType=LONGVARBINARY}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RegexRegisteredServiceWithBLOBs record) {
        SQL sql = new SQL();
        sql.UPDATE("regexregisteredservice");

        if (record.getExpressionType() != null) {
            sql.SET("expression_type = #{expressionType,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getEvaluationOrder() != null) {
            sql.SET("evaluation_order = #{evaluationOrder,jdbcType=INTEGER}");
        }
        
        if (record.getInformationurl() != null) {
            sql.SET("informationUrl = #{informationurl,jdbcType=VARCHAR}");
        }
        
        if (record.getLogo() != null) {
            sql.SET("logo = #{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getLogoutType() != null) {
            sql.SET("logout_type = #{logoutType,jdbcType=INTEGER}");
        }
        
        if (record.getLogoutUrl() != null) {
            sql.SET("logout_url = #{logoutUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getPrivacyurl() != null) {
            sql.SET("privacyUrl = #{privacyurl,jdbcType=VARCHAR}");
        }
        
        if (record.getServiceid() != null) {
            sql.SET("serviceId = #{serviceid,jdbcType=VARCHAR}");
        }
        
        if (record.getTheme() != null) {
            sql.SET("theme = #{theme,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessStrategy() != null) {
            sql.SET("access_strategy = #{accessStrategy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getAttributeRelease() != null) {
            sql.SET("attribute_release = #{attributeRelease,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getExpirationPolicy() != null) {
            sql.SET("expiration_policy = #{expirationPolicy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getMfaPolicy() != null) {
            sql.SET("mfa_policy = #{mfaPolicy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getProxyPolicy() != null) {
            sql.SET("proxy_policy = #{proxyPolicy,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getPublicKey() != null) {
            sql.SET("public_key = #{publicKey,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getRequiredHandlers() != null) {
            sql.SET("required_handlers = #{requiredHandlers,jdbcType=LONGVARBINARY}");
        }
        
        if (record.getUsernameAttr() != null) {
            sql.SET("username_attr = #{usernameAttr,jdbcType=LONGVARBINARY}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    public String pageSelect(@Param("start") int start,@Param("rows") int rows){
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT * ");
      sql.append("FROM regexregisteredservice ");
      sql.append("LIMIT "+start+","+rows);

      return sql.toString();
    }

    public String totalNum(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(id) ");
        sql.append("FROM regexregisteredservice ");
        return sql.toString();
    }

    public String selectAll(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("regexregisteredservice");
        return sql.toString();
    }
}