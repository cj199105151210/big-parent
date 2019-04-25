/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: UserGroupPrivateController
 * Author:   Jack Ma
 * Date:     2019/3/14 11:17
 * Description: 个人组管理controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.controller;

import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.user.center.service.UserGroupPrivateService;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndPrivateGroup;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroupPrivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userGroupPrivate")
public class UserGroupPrivateController {

    private static final Logger logger = LoggerFactory.getLogger(UserGroupPrivateController.class);

    @Autowired
    UserGroupPrivateService userGroupPrivateService;

    @Autowired
    MinioTemplate template;

    @Value("${bucketName}")
    private String BUCKETNAME;

    /**
     * 新增节点
     *
     * @param groupPrivate
     * @return
     */
    @PostMapping("insertNode")
    public R insertNode(@RequestBody UserGroupPrivate groupPrivate) {
        // 校验名称是否重复
        int flag = userGroupPrivateService.checkName(groupPrivate.getCname());
        if (flag == 0) {
            // 新增数据
            return new R<>(userGroupPrivateService.insertNode(groupPrivate));
        } else {
            return new R<>("msg", "is_exist");
        }
    }

    /**
     * 更新节点
     *
     * @param groupPrivate
     * @return
     */
    @PostMapping("updateNode")
    public R updateNode(@RequestBody UserGroupPrivate groupPrivate) {
        // 校验名称是否重复
        int flag = userGroupPrivateService.checkName(groupPrivate.getCname());
        if (flag == 0) {
            // 更新数据
            return new R<>(userGroupPrivateService.updateNode(groupPrivate));
        } else {
            return new R<>("msg", "is_exist");
        }
    }

    /**
     * 根据ID删除节点（逻辑删除）
     *
     * @param nodeId
     * @return
     */
    @GetMapping("deleteNodeById")
    public R deleteNodeById(String nodeId) {
        return new R<>(userGroupPrivateService.deleteNodeById(nodeId));
    }

    /**
     * 根据节点ID查询人员信息
     *
     * @param nodeId
     * @return
     */
    @GetMapping("selectUserByNodeId")
    public R selectUserByNodeId(String nodeId) {
        List<UserMessageVo> list = userGroupPrivateService.selectUserByNodeId(nodeId);
        return new R<>(list);
    }

    /**
     * 根据姓名查询人员信息
     *
     * @param xm
     * @return
     */
    @GetMapping("selectUserByXM")
    public R selectUserByXM(String xm) {
        return new R<>(userGroupPrivateService.selectUserByXM(xm));
    }

    /**
     * 新增用户私组与用户关系数据
     * @param userAndPrivateGroup
     * @return
     */
    @PostMapping("insertToRelation")
    public R insertToRelation(UserAndPrivateGroup userAndPrivateGroup) {
        return new R<>(userGroupPrivateService.insertToRelation(userAndPrivateGroup));
    }

    /**
     * 删除用户私组与用户关系数据
     * @param userAndPrivateGroup
     * @return
     */
    @PostMapping("deleteFromRelation")
    public R deleteFromRelation(UserAndPrivateGroup userAndPrivateGroup) {
        return new R<>(userGroupPrivateService.deleteFromRelation(userAndPrivateGroup));
    }

    /**
     * 根据姓名、姓名首字母和电话查询个人组下用户信息
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @GetMapping("selectUserByNamePhone")
    public R selectUserByNamePhone (String queryParam){
        //查询人员信息
        List<UserMessageVo> list = userGroupPrivateService.selectUserByNamePhone(queryParam);
        String url = "";
        for (UserMessageVo userMessage : list) {
            //获取头像url
            try {
                if (userMessage.getAvator() != null && !userMessage.getAvator().isEmpty()) {
                    url = template.getObjectURL(BUCKETNAME, userMessage.getAvator(),604800);
                } else {
                    url = "";
                }
            } catch (Exception e) {
                logger.info("拼装头像url失败！"+e);
            } finally {
                userMessage.setPhotoUrl(url);
            }
        }
        return new R<>(list);
    }
}
