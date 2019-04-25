package com.bosssoft.bigdata.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: bigdata-parent
 * @description: 全局返回码说明【待完善】
 * @author: Mr.Lucky
 * @create: 2019-03-06 15:18
 **/
@Getter
@AllArgsConstructor
public enum GlobalReturnCodeEnum {

    /**
     * 系统繁忙，此时请开发者稍候再试
     */
    G1("-1", "系统繁忙，此时请开发者稍候再试"),

    /**
     * 请求成功
     */
    SUCCESS("0", "请求成功");

    /**
     * 类型
     */
    private final String status;
    /**
     * 描述
     */
    private final String description;
}
