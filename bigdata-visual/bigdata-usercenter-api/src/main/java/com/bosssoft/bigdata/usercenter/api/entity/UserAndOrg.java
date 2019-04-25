package com.bosssoft.bigdata.usercenter.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserAndOrg {
    private String userId;

    private String orgId;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String isDirectly;

}