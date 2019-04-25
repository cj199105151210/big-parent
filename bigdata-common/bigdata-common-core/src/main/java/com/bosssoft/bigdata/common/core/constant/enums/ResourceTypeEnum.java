package com.bosssoft.bigdata.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lucky
 * @date 2019/2/18
 * 资源类型
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {
    /**
     * 图片资源
     */
    IMAGE("image", "图片资源"),

    /**
     * xml资源
     */
    XML("xml", "xml资源"),

    /**
     * json资源
     */
    JSON("json", "json资源");
    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String description;
}
