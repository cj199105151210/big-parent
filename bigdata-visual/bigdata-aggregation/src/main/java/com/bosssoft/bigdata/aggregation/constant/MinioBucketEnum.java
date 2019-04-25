/**
 * Copyright (C), 2019, 福建博思
 * FileName: MinioBucketEnum
 * Author:   lifei
 * Date:     2019/3/27 09:35
 * Description: 常量类
 * version:  1.0
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.aggregation.constant;

public enum MinioBucketEnum {//MinioBucketEnum
    BUCKETNAME_SLIDESHOW("aggr-slideshow"),
    BUCKETNAME_LIGHTAPP("aggr-lightapp");

    private String name;

    private MinioBucketEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
