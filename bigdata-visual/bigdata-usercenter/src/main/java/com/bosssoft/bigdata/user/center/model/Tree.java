/**
 * Copyright (C), 2019, 福建博思
 * FileName: Tree
 * Author:   mapengpeng
 * Date:     2019/3/13 9:54
 * Description: 树形结构实体类
 * version:  1.0
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.model;

import com.bosssoft.bigdata.common.data.annotation.DictClass;
import com.bosssoft.bigdata.common.data.annotation.DictParam;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.List;

@Data
@DictClass
public class Tree {

    private String id;

    private String name;

    private String pId;

    private String code;

    private String nodeType; // 节点类型：0人员、1非人员

    private String loginName;

    private String parentName;

    @DictParam(dictType = "user_position", nameFiled = "dutyName")
    private Integer duty;//职位字典值，通过缓存获取dutyName

    private String dutyName;//职位名称：nodeType为0时才可能有值

    private String photoUrl;//头像url：nodeType为0时才可能有值

    private Integer childCount;//子节点个数

    private List<Tree> children;
}
