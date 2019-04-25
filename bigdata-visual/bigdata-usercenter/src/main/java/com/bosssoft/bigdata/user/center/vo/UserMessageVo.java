/**
 * Copyright (C), 2019, 福建博思
 * FileName: UserMessageVo
 * Author:   mapengpeng
 * Date:     2019/3/26 17:50
 * Description: 用户信息包装类，用来展示除数据库字段外其他信息
 * version:  1.0
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.vo;

import com.bosssoft.bigdata.common.data.annotation.DictClass;
import com.bosssoft.bigdata.common.data.annotation.DictParam;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import lombok.Data;

@Data
@DictClass
public class UserMessageVo extends UserMessage {
    @DictParam(dictType = "user_position", nameFiled = "dutyName")
    private Integer duty;

    private String dutyName;

    private String orgName;

    private String orgId;

    private String photoUrl;
}
