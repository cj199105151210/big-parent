package com.bosssoft.bigdata.usercenter.api.entity;

import lombok.Data;

@Data
public class SysOauthClientDetails {
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

    private Integer tenantId;


}