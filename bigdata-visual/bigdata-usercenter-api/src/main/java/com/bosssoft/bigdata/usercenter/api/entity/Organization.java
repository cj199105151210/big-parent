package com.bosssoft.bigdata.usercenter.api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Organization {
    private String guid;

    private String name;

    private String cname;

    private String shortName;

    private String code;

    private String parentId;

    private Integer sortNo;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String createUserName;

    private String extend;

    private String description;

    private String orgType;

    private String isEffective;

    private String isEntity;
}