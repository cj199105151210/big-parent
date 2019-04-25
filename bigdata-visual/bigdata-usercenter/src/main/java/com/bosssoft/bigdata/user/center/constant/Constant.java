/**
 * Copyright (C), 2019, 福建博思
 * FileName: Constant
 * Author:   mapengpeng
 * Date:     2019/3/26 16:16
 * Description: 常量类
 * version:  1.0
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.constant;

public interface Constant {
    /**
     * 表类型常量
     */
    String TABLE_ORGANIZATION = "1";// 机构表
    String TABLE_USER_GROUP = "2";// 用户组管理表
    String TABLE_FLOW_POSITION = "3";// 流程岗位表
    String TABLE_PRIVATE_GROUP = "4";// 个人组表
    /**
     *用户所属类别常量
     */
    String USER_USER = "1";// 用户所属类别：用户
    String POSITION_USER = "2";// 用户所属类别：岗位
    String ORGANIZATION_USER = "3";// 用户所属岗位：机构
}
