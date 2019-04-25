/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: UserGroupController
 * Author:   Jack Ma
 * Date:     2019/3/11 14:17
 * Description: 用户组管理controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.controller;

import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.user.center.model.RelationParam;
import com.bosssoft.bigdata.user.center.model.Tree;
import com.bosssoft.bigdata.user.center.service.UserGroupService;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndGroup;
import com.bosssoft.bigdata.usercenter.api.entity.UserAndOrg;
import com.bosssoft.bigdata.usercenter.api.entity.UserGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("userGroup")
public class UserGroupController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserGroupService userGroupService;
    @Autowired
    MinioTemplate template;

    @Value("${bucketName}")
    private String BUCKETNAME;

    @GetMapping("getAll")
    public R getAllUserGroup(){
        UserGroup record = new UserGroup();
        record.setIsDelete(0);
        List<UserGroup> list = userGroupService.selectAllUserGroup(record);
        return new R<>(list);
    }

    /**
     * 查找树状列表数据
     *
     * @param type 表类型：1机构管理、2用户组管理、3流程岗位管理、4用户私组表
     * @param isShowCrew 树中是否显示人员信息：0显示、1不显示
     * @return
     */
    @GetMapping("selectTreeList")
    public R selectTreeList(String type, String isShowCrew) {
        // 根据type查询全部表数据
        List<Tree> treeData = userGroupService.selectAllTreeData(type,null);
        String url = "";
        if ("0".equals(isShowCrew)) {
            // 根据type查询全部人员数据
            List<Tree> crewData = userGroupService.selectAllUsers(type,null);
            for (Tree crew : crewData) {
                // 确定节点类型
                crew.setNodeType("0");
                //获取头像url
                try {
                    if (crew.getPhotoUrl() != null && !crew.getPhotoUrl().isEmpty()) {
                        url = template.getObjectURL(BUCKETNAME, crew.getPhotoUrl(),604800);
                    } else {
                        url = "";
                    }
                } catch (Exception e) {
                    logger.info("拼装头像url失败！"+e);
                } finally {
                    crew.setPhotoUrl(url);
                }
            }
            treeData.addAll(crewData);
        }

        return new R<>(getTreeData(treeData));
    }

    /**
     * 根据节点Id查找节点数据
     * @param type 表类型：1机构管理、2用户组管理、3流程岗位管理、4用户私组表
     * @param isShowCrew 树中是否显示人员信息：0显示、1不显示
     * @param nodeId 节点Id
     * @return
     */
    @GetMapping("selectTreeByNodeId")
    public R selectTreeByNodeId (String type, String isShowCrew, String nodeId) {
        // 根据type、nodeId查询数据
        List<Tree> treeData = userGroupService.selectTreeDataByNodeId(type,nodeId);
        String url = "";
        if ("0".equals(isShowCrew)) {
            // 根据type、nodeId查询人员数据
            List<Tree> crewData = userGroupService.selectAllUsers(type,nodeId);
            for (Tree crew : crewData) {
                // 确定节点类型
                crew.setNodeType("0");
                //获取头像url
                try {
                    if (crew.getPhotoUrl() != null && !crew.getPhotoUrl().isEmpty()) {
                        url = template.getObjectURL(BUCKETNAME, crew.getPhotoUrl(),604800);
                    } else {
                        url = "";
                    }
                } catch (Exception e) {
                    logger.info("拼装头像url失败！"+e);
                } finally {
                    crew.setPhotoUrl(url);
                }
            }
            treeData.addAll(crewData);
        }
        List<Tree> resultList = getTreeData(treeData);
        List<Tree> crewList;
        // 遍历获取子节点数量
        for (Tree tree : resultList) {
            if (!"0".equals(tree.getNodeType())) {
                if ("0".equals(isShowCrew)) {
                    crewList = userGroupService.selectAllUsers(type,tree.getId());
                } else {
                    crewList = new ArrayList<>();
                }
                tree.setChildCount(crewList.size());
            }
        }
        return new R<>(getTreeData(resultList));
    }

    /**
     * 将查到的数据整理成树
     * @param treeData
     * @return
     */
    private List<Tree> getTreeData(List<Tree> treeData) {
        List<Tree> resultList = new ArrayList<>();
        Map<Object, Object> treeMap = new HashMap();
        Object itemTree;
        int size = treeData.size();
        if (size > 0) {
            // 遍历把所有数据都放到map中
            for (int i = 0; i < size; i++) {
                itemTree = treeData.get(i);
                treeMap.put(treeData.get(i).getId(), treeData.get(i));
            }
            // 再次遍历将顶层节点放入list集合
            for (int i = 0; i < size; i++) {
                if (!treeMap.containsKey(treeData.get(i).getPId())) {
                    resultList.add(treeData.get(i));
                }
            }
            // 循环数据，查询子节点
            for (int i = 0; i < size; i++) {
                // 从map集合中找到父节点
                Tree tree = (Tree) treeMap.get(treeData.get(i).getPId());
                if (tree != null) {
                    // 将子节点添加到父节点的children集合下
                    if (tree.getChildren() == null) {
                        tree.setChildren(new ArrayList<Tree>());
                    }
                    // 将父节点名称放入tree中
                    treeData.get(i).setParentName(tree.getName());
                    tree.getChildren().add(treeData.get(i));
                    // 把放好的数据放回到map中
                    treeMap.put(treeData.get(i).getPId(), tree);
                }
            }
        }
        return resultList;
    }

    /**
     * 新增节点
     *
     * @param userGroup
     * @return
     */
    @PostMapping("insertNode")
    public R insertNode(@RequestBody UserGroup userGroup) {
        // 校验名称是否重复
        int flag = userGroupService.checkName(userGroup.getCname());
        if (flag == 0) {
            // 新增数据
            return new R<>(userGroupService.insertNode(userGroup));
        } else {
            return new R<>("msg", "is_exist");
        }
    }

    /**
     * 更新节点
     *
     * @param userGroup
     * @return
     */
    @PostMapping("updateNode")
    public R updateNode(@RequestBody UserGroup userGroup) {
        // 校验名称是否重复
        int flag = userGroupService.checkName(userGroup.getCname());
        if (flag == 0) {
            // 更新数据
            return new R<>(userGroupService.updateNode(userGroup));
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
        return new R<>(userGroupService.deleteNodeById(nodeId));
    }

    /**
     * 根据节点ID查询人员信息
     *
     * @param nodeId
     * @return
     */
    @GetMapping("selectUserByNodeId")
    public R selectUserByNodeId(String nodeId) {
        return new R<>(userGroupService.selectUserByNodeId(nodeId));
    }

    /**
     * 根据类型和姓名模糊查询人员信息
     * @param type 表类型：1机构管理、2用户组管理、3流程岗位管理、4用户私组表
     * @param xm 姓名
     * @return
     */
    @GetMapping("selectUserByTypeXM")
    public R selectUserByTypeXM(String type, String xm) {
        return new R<>(userGroupService.selectUserByTypeXM(type,xm));
    }

    /**
     * 新增用户组与用户关系数据
     * @param userAndGroup
     * @return
     */
    @PostMapping("insertToRelation")
    public R insertToRelation(UserAndGroup userAndGroup) {
        return new R<>(userGroupService.insertToRelation(userAndGroup));
    }

    /**
     * 删除用户组与用户关系数据
     * @param userAndGroup
     * @return
     */
    @PostMapping("deleteFromRelation")
    public R deleteFromRelation(UserAndGroup userAndGroup) {
        return new R<>(userGroupService.deleteFromRelation(userAndGroup));
    }

    /**
     * 调整人员与机构、用户组、流程岗位和个人组之间的关系
     * @param relationParam 参数实体类
     * @return
     */
    @PostMapping("adjustRelation")
    public R adjustRelation(@RequestBody RelationParam relationParam) {
        String orgId = relationParam.getOrgId();
        UserAndOrg userAndOrg = new UserAndOrg();
        userAndOrg.setOrgId(orgId);
        userAndOrg.setUserId(relationParam.getUserId());
        //调整关系
        userGroupService.adjustRelation(relationParam, userAndOrg);
        //查询表格数据
        List<UserMessageVo> list;
        if ("0".equals(relationParam.getAdjustType())) {
            list = userGroupService.selectUserByParentId(relationParam.getTargetType(),relationParam.getTargetId());
        } else {
            list = userGroupService.selectUserByParentId(relationParam.getType(),orgId);
        }
        return new R<>(list);
    }


    /**
     * 根据用户id查询所在所有用户组
     * @param userId
     * @return
     */
    @GetMapping("/selectUserGroup")
    public R selectUserGroup(String userId){
        List list = userGroupService.selectUserGroup(userId);
        return  new R(list);
    }

    /**
     * 根据关键字和用户id模糊查询，用户所属部门
     * @param userId
     * @param keyWord
     * @return
     */
    @GetMapping("/fuzzSearch")
    public R fuzzSearch(@Param("userId")String  userId, @Param("keyWord")String keyWord){
        List list = userGroupService.fuzzSearch(userId,keyWord);
        return new R(list);
    }

    /**
     * 根据节点ID分页查询人员信息
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
        List<UserMessageVo> list = userGroupService.selectUserByNodeId(nodeId);
        PageInfo<UserMessageVo> pageInfo = new PageInfo<>(list);
        return new R<>(pageInfo);
    }

    /**
     * 根据ID查询人员信息（不分页）
     *
     * @param nodeId 节点id
     * @return
     */
    @GetMapping("selectAllUserByNodeId")
    public R selectUserPageByNodeId(@RequestParam(value = "nodeId", required = true) String nodeId) {
        List<UserMessageVo> list = userGroupService.selectUserByNodeId(nodeId);
        return new R<>(list);
    }

    /**
     * 根据姓名、姓名首字母和电话查询用户组下用户信息
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @GetMapping("selectUserByNamePhone")
    public R selectUserByNamePhone (String queryParam){
        //查询人员信息
        List<UserMessageVo> list = userGroupService.selectUserByNamePhone(queryParam);
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

    /**
     * 根据类型查询用户组或个人组信息
     * @param type 查询类型：2用户组、4个人组
     * @param userId 登录用户id：查询个人组时使用
     * @return
     */
    @GetMapping("selectUserInfoByType")
    public R selectUserInfoByType(String type,String userId) {
        // 根据type查询树数据
        List<Tree> treeData = userGroupService.selectAllTreeData(type, userId);
        if (treeData.isEmpty()){
            return new R<>();
        }
        // 根据type查询人员信息
        List<Tree> users = userGroupService.selectAllUsers(type,null);
        // 将人员信息放到tree中
        String url = "";
        for (Tree tree : users) {
            //设置树节点类型为人员类型
            tree.setNodeType("0");
            //获取头像url
            try {
                if (tree.getPhotoUrl() != null && !tree.getPhotoUrl().isEmpty()) {
                    url = template.getObjectURL(BUCKETNAME, tree.getPhotoUrl(),604800);
                } else {
                    url = "";
                }
            } catch (Exception e) {
                logger.info("拼装头像url失败！"+e);
            } finally {
                tree.setPhotoUrl(url);
            }
        }
        //整理树数据
        treeData.addAll(users);
        List<Tree> treeList = getTreeData(treeData);

        return new R<>(treeList);
    }

    /**
     * 根据id查询用户组信息
     * @param nodeId
     * @return
     */
    @GetMapping("selectUserGroupById")
    public R selectUserGroupById(String nodeId){
        UserGroup userGroup = userGroupService.selectByPrimaryKey(nodeId);
        return new R(userGroup);
    }

}
