package com.bosssoft.bigdata.usercenter.api.entity;

import lombok.Data;

@Data
public class RegexRegisteredService {
    private Long id;

    private String expressionType;

    private String description;

    private Integer evaluationOrder;

    private String informationurl;

    private String logo;

    private Integer logoutType;

    private String logoutUrl;

    private String name;

    private String privacyurl;

    private String serviceid;

    private String theme;
}