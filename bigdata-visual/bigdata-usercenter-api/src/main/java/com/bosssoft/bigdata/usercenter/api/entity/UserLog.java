package com.bosssoft.bigdata.usercenter.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;

@Data
public class UserLog implements Serializable {

    @Id
    private String guid;

    private String userName;

    private String phone;

    private Integer loginType;

    private Date optTime;

    private Integer optType;

}