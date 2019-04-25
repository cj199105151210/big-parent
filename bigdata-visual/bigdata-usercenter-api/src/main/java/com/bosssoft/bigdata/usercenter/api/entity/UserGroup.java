package com.bosssoft.bigdata.usercenter.api.entity;

import com.bosssoft.bigdata.usercenter.api.annotation.OrderType;
import com.bosssoft.bigdata.usercenter.api.constant.Constant;
import lombok.Data;

import java.util.Date;

@Data
public class UserGroup {
    private String guid;

    private String cname;

    private String shortName;

    private String code;

    private String parentId;

    @OrderType
    private Integer sortNo;

    private Integer isDelete;

    private Date createTime;

    private Date updateTme;

    private String createUser;

    private String createUserName;

    private String extend;

    private String description;

}