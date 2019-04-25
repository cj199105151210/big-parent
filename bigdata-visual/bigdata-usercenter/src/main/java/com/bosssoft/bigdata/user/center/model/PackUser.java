package com.bosssoft.bigdata.user.center.model;

import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import lombok.Data;

@Data
public class PackUser {
    private UserMessage userMessage;

    private String url;


}
