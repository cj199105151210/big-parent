package com.bosssoft.bigdata.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-03-21 09:39
 */
@Getter
@AllArgsConstructor
public enum LoginErrorEnum {

    /**
     * 用户不存在
     */
    NO_USER("1","用户不存在"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR("2","用户密码错误"),

    /**
     * 用户被锁定
     */
    USER_LOCKED("3","由于登录失败次数太多，该用户已被锁定，请三十分钟后再登录"),

    USER_OR_PASSWORD("4","用户名或密码错误")
            ;

    private String type;

    private String desc;
}
