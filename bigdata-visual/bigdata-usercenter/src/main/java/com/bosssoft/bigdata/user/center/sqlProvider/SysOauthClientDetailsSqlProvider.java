package com.bosssoft.bigdata.user.center.sqlProvider;

import com.bosssoft.bigdata.usercenter.api.entity.SysOauthClientDetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SysOauthClientDetailsSqlProvider {

    public String insertSelective(SysOauthClientDetails record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_oauth_client_details");
        
        if (record.getClientId() != null) {
            sql.VALUES("client_id", "#{clientId,jdbcType=VARCHAR}");
        }
        
        if (record.getResourceIds() != null) {
            sql.VALUES("resource_ids", "#{resourceIds,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.VALUES("client_secret", "#{clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.VALUES("scope", "#{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorizedGrantTypes() != null) {
            sql.VALUES("authorized_grant_types", "#{authorizedGrantTypes,jdbcType=VARCHAR}");
        }
        
        if (record.getWebServerRedirectUri() != null) {
            sql.VALUES("web_server_redirect_uri", "#{webServerRedirectUri,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorities() != null) {
            sql.VALUES("authorities", "#{authorities,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessTokenValidity() != null) {
            sql.VALUES("access_token_validity", "#{accessTokenValidity,jdbcType=INTEGER}");
        }
        
        if (record.getRefreshTokenValidity() != null) {
            sql.VALUES("refresh_token_validity", "#{refreshTokenValidity,jdbcType=INTEGER}");
        }
        
        if (record.getAdditionalInformation() != null) {
            sql.VALUES("additional_information", "#{additionalInformation,jdbcType=VARCHAR}");
        }
        
        if (record.getAutoapprove() != null) {
            sql.VALUES("autoapprove", "#{autoapprove,jdbcType=VARCHAR}");
        }
        
        if (record.getTenantId() != null) {
            sql.VALUES("tenant_id", "#{tenantId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SysOauthClientDetails record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_oauth_client_details");
        
        if (record.getResourceIds() != null) {
            sql.SET("resource_ids = #{resourceIds,jdbcType=VARCHAR}");
        }
        
        if (record.getClientSecret() != null) {
            sql.SET("client_secret = #{clientSecret,jdbcType=VARCHAR}");
        }
        
        if (record.getScope() != null) {
            sql.SET("scope = #{scope,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorizedGrantTypes() != null) {
            sql.SET("authorized_grant_types = #{authorizedGrantTypes,jdbcType=VARCHAR}");
        }
        
        if (record.getWebServerRedirectUri() != null) {
            sql.SET("web_server_redirect_uri = #{webServerRedirectUri,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthorities() != null) {
            sql.SET("authorities = #{authorities,jdbcType=VARCHAR}");
        }
        
        if (record.getAccessTokenValidity() != null) {
            sql.SET("access_token_validity = #{accessTokenValidity,jdbcType=INTEGER}");
        }
        
        if (record.getRefreshTokenValidity() != null) {
            sql.SET("refresh_token_validity = #{refreshTokenValidity,jdbcType=INTEGER}");
        }
        
        if (record.getAdditionalInformation() != null) {
            sql.SET("additional_information = #{additionalInformation,jdbcType=VARCHAR}");
        }
        
        if (record.getAutoapprove() != null) {
            sql.SET("autoapprove = #{autoapprove,jdbcType=VARCHAR}");
        }
        
        if (record.getTenantId() != null) {
            sql.SET("tenant_id = #{tenantId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("client_id = #{clientId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String updateSelective(@Param("clientId") String clientId, @Param("clientSecret") String clientSecret, @Param("oldClientId") String oldClientId) {
        SQL sql = new SQL();
        sql.UPDATE("sys_oauth_client_details");
            sql.SET("client_id = #{clientId}");
            sql.SET("client_secret = #{clientSecret}");
        sql.WHERE("client_id = #{oldClientId}");

        return sql.toString();
    }

    public String selectAll(){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("sys_oauth_client_details");

        return sql.toString();
    }
}