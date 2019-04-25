/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: OrganizationController
 * Author:   Jack Ma
 * Date:     2019/3/11 14:17
 * Description: 机构管理controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.controller;

import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.user.center.service.OrganizationService;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.Organization;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    OrganizationService organizationService;

    @Autowired
    MinioTemplate template;

    @Value("${bucketName}")
    private String BUCKETNAME;


    /**
     * 新增节点
     *
     * @param org
     * @return
     */
    @PostMapping("insertNode")
    public R insertNode(@RequestBody Organization org) {
        // 校验名称是否重复
        int flag1 = organizationService.checkName(org, "0");
        // 校验编码是否重复
        int flag2 =organizationService.checkCode(org, "0");
        if (flag1 == 0) {
            if (flag2 == 0) {
                // 新增数据
                return new R<>(organizationService.insert(org));
            } else {
                return new R<>("msg", "exist_code");
            }
        } else {
            return new R<>("msg", "exist_cname");
        }
    }

    /**
     * 更新节点
     *
     * @param org
     * @return
     */
    @PostMapping("updateNode")
    public R updateNode(@RequestBody Organization org) {
        // 校验名称是否重复
        int flag = organizationService.checkName(org, "1");
        // 校验编码是否重复
        int flag2 =organizationService.checkCode(org, "1");
        if (flag == 0) {
            if (flag2 == 0) {
                // 更新数据
                return new R<>(organizationService.update(org));
            } else {
                return new R<>("msg", "exist_code");
            }
        } else {
            return new R<>("msg", "exist_cname");
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
        return new R<>(organizationService.delete(nodeId));
    }

    /**
     * 根据节点ID查询人员信息
     *
     * @param nodeId
     * @return
     */
    @GetMapping("selectUserByNodeId")
    public R selectUserByNodeId(@RequestParam(value = "nodeId", required = true) String nodeId) {
        String url = "";
        List<UserMessageVo> list = organizationService.selectUserByNodeId(nodeId);
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

    /**
     * 根据姓名查询人员信息
     *
     * @param xm
     * @return
     */
    @GetMapping("selectUserByXM")
    public R selectUserByXM(String xm) {
        return new R<>(organizationService.selectUserByXM(xm));
    }

    /**
     * 新增机构与用户关系数据
     * @param userAndOrg
     * @return
     */
    @PostMapping("insertToRelation")
    public R insertToRelation(UserAndOrg userAndOrg) {
        return new R<>(organizationService.insertToRelation(userAndOrg));
    }

    /**
     * 删除机构与用户关系数据
     * @param userAndOrg
     * @return
     */
    @PostMapping("deleteFromRelation")
    public R deleteFromRelation(UserAndOrg userAndOrg) {
        return new R<>(organizationService.deleteFromRelation(userAndOrg));
    }

    /**
     * 根据机构id查询机构信息
     * @param orgId
     * @return
     */
    @GetMapping("selectOrganization")
    public R selectOrganization(String orgId){
        Organization org = organizationService.selectOrgMessage(orgId);
        return new R(org);
    }

    /**
     * 根据用户Id查询，用户所属的部门
     * @param userId
     * @return
     */
    @GetMapping("selectUserOrg")
    public R selectUserOrg(String userId){
        List list = organizationService.selectUserOrg(userId);
        return new R(list);
    }

    /**
     * 根据关键字和用户id模糊查询，用户所属部门
     * @param userId
     * @param keyWord
     * @return
     */
    @GetMapping("/fuzzSearch")
    public R fuzzSearch(@Param("userId")String  userId,@Param("keyWord")String keyWord){
        List list = organizationService.fuzzSearch(userId,keyWord);
        return new R(list);
    }

    /**
     *修改直属部门
     * @param userAndOrg
     * @return
     */
    @PostMapping("/updateDirectly")
    public R updateDirectly(UserAndOrg userAndOrg){
        int code = organizationService.updateDirectly(userAndOrg);
        if(code==1){
            return new R("result", "修改成功");
        }
        return  new  R(new Exception("修改出错"));
    }

    /**
     * 获取全部组织机构
     * @return
     */
    @GetMapping("getAll")
    public R getAll(){
        Organization organization = new Organization();
        organization.setIsDelete(0);
        List<Organization> list = organizationService.selectAllByRecord(organization);
        return new R<>(list);
    }

    /**
     * 根据机构ID分页查询人员信息
     *
     * @param nodeId 节点id
     * @param currentPage 当前页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("selectUserPageByNodeId")
    public R selectUserPageByNodeId(@RequestParam(value = "nodeId", required = true) String nodeId,
                                int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<UserMessageVo> list = organizationService.selectUserByNodeId(nodeId);
        PageInfo<UserMessageVo> pageInfo = new PageInfo<>(list);
        return new R<>(pageInfo);
    }

    /**
     * 根据机构ID查询人员信息（不分页）
     *
     * @param nodeId 节点id
     * @return
     */
    @GetMapping("selectAllUserByNodeId")
    public R selectUserPageByNodeId(@RequestParam(value = "nodeId", required = true) String nodeId) {
        List<UserMessageVo> list = organizationService.selectUserByNodeId(nodeId);
        return new R<>(list);
    }

    /**
     * 根据姓名、姓名首字母和电话查询机构下用户信息
     *
     * @param orgId 上级机构id
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @GetMapping("selectUserByNamePhone")
    public R selectUserByNamePhone(String orgId, String queryParam) {
        List<UserMessageVo> list = organizationService.selectUserByNamePhone(orgId, queryParam);
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
