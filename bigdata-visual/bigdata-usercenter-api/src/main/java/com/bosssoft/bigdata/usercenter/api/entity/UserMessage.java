package com.bosssoft.bigdata.usercenter.api.entity;

import lombok.Data;

@Data
public class UserMessage{
    private String guid;

    private String loginName;

    private Integer cardType;

    private String idCard;

    private String xm;

    private Integer xb;

    private Integer age;

    private String userNo;

    private Integer type;

    private String password;

    private String phone;

    private String mail;

    private String avator;

    private Integer sortNo;

    private Integer duty;

    private Integer errNo;

    private String thirdName;

    private String qqOpenid;

    private String wxOpenid;

    private Integer isDelete;

    private Integer isAuth;

    private String extend;

    private Integer firstLogin;

    private String firstLetter;

    private String description;
}