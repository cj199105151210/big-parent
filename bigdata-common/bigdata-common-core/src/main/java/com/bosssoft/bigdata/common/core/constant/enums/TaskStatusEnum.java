package com.bosssoft.bigdata.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lucky
 * @date 2019/2/18
 * 流程状态
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {
    /**
     * 未提交
     */
    UNSUBMIT("0", "未提交"),

    /**
     * 审核中
     */
    CHECK("1", "审核中"),

    /**
     * 已完成
     */
    COMPLETED("2", "已完成"),

    /**
     * 驳回
     */
    OVERRULE("9", "驳回");

    /**
     * 类型
     */
    private final String status;
    /**
     * 描述
     */
    private final String description;
}
