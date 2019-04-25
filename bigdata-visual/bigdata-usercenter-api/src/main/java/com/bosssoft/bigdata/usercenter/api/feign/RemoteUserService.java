package com.bosssoft.bigdata.usercenter.api.feign;

import com.bosssoft.bigdata.common.core.constant.ServiceNameConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = ServiceNameConstants.USERCENTER_SERVICE)
public interface RemoteUserService {


    /**
     * 根据登录名，查询当前用户是否存在
     * @param userName
     * @return
     */
    @PostMapping("/login/checkUserName")
    R<UserMessage> checkUserName(@RequestParam("userName") String userName);

    /**
     * 根据登录名获取用户信息
     * @param loginName
     * @return
     */
    @GetMapping("/login/selectByLoginName")
    R<UserMessage> getUserInfo(@RequestParam("loginName")String loginName);

    /**
     * 更新用户信息
     * @param userMessage
     * @return
     */
    @PostMapping("/user/update")
    R updateUserInfo(@RequestBody UserMessage userMessage);

    @GetMapping("/user/selectUserInfo")
    R selectUserInfo();

    /**
     * 根据用户名，增加错误次数
     * @param loginName
     * @return
     */
    @PostMapping("/user/increasePasswordByLoginName")
    R increasePasswordByLoginName(@RequestParam("loginName")String loginName);

    /**
     * 用户注册接口
     * @param userMessage
     * @return
     */
    @PostMapping("/user/register")
    R registryUser(@RequestBody UserMessage userMessage);

    /**
     * 获取全部用户组
     * @return
     */
    @GetMapping("/userGroup/getAll")
    R getAllUserGroup();

    /**
     * 获取全部流程岗位
     * @return
     */
    @GetMapping("/flowPosition/getAll")
    R getAllFlowPosition();

    /**
     *  获取全部组织机构
     * @return
     */
    @GetMapping("/organization/getAll")
    R getAllOrganization();

    /**
     * 根据节点Id查找子节点数据和人员数据
     * @param type 表类型：1机构管理、2用户组管理、3流程岗位管理、4用户私组表
     * @param isShowCrew 树中是否显示人员信息：0显示、1不显示
     * @param nodeId 节点Id
     * @return
     */
    @GetMapping("/userGroup/selectTreeByNodeId")
    R selectTreeByNodeId(@RequestParam("type") String type,@RequestParam("isShowCrew") String isShowCrew,
                         @RequestParam("nodeId") String nodeId);

    /**
     * 根据类型用户名分页查询用户信息
     *
     * @param type 类型：1用户、2岗位、3机构
     * @param name 根据类型分别为用户名、岗位名和机构名
     * @param currentPage 当前页
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("selectUserForPage")
    R selectUserForPage (@RequestParam("type") String type, @RequestParam("name") String name,
                         @RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize);






}

