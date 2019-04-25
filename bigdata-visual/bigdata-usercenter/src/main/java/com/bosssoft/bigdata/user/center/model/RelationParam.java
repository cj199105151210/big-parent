/**
 * Copyright (C), 2019, 福建博思
 * FileName: RelationParam
 * Author:   mapengpeng
 * Date:     2019/4/3 16:06
 * Description: 调整人员与机构、用户组、流程岗位和个人组之间的关系参数实体类
 * version:  1.0
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.model;

import lombok.Data;

@Data
public class RelationParam {
    private String adjustType;

    private String type;

    private String targetType;

    private String targetId;

    private String userId;

    private String orgId;
}
