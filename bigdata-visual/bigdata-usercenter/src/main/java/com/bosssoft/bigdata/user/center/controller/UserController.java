package com.bosssoft.bigdata.user.center.controller;


import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.core.utils.StringUtil;
import com.bosssoft.bigdata.common.minio.service.MinioTemplate;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import com.bosssoft.bigdata.user.center.model.PackUser;
import com.bosssoft.bigdata.user.center.service.FlowPositionService;
import com.bosssoft.bigdata.user.center.service.OrganizationService;
import com.bosssoft.bigdata.user.center.service.UserGroupService;
import com.bosssoft.bigdata.user.center.service.UserService;
import com.bosssoft.bigdata.user.center.utils.ChineseToFirstLetterUtil;
import com.bosssoft.bigdata.user.center.utils.CommonUtils;
import com.bosssoft.bigdata.user.center.vo.UserMessageVo;
import com.bosssoft.bigdata.usercenter.api.entity.Organization;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 *该控制类主要负责用户数据的增删改查
 */

@RequestMapping("user")
@RestController
public class UserController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FlowPositionService flowPositionService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    MinioTemplate template;

    @Value("${bucketName}")
    private String BUCKETNAME;


    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R insertUser(@RequestBody UserMessage user) {
        //获取密码并加密
        String psw = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(psw));

        //获取姓名首字母
        if (StringUtil.isNotBlank(user.getXm())) {
            String firstLetter = ChineseToFirstLetterUtil.ChineseToFirstLetter(user.getXm().trim());
            user.setFirstLetter(firstLetter);
        }
        //插入用户
        int code = userService.insertUser(user);
        if (code == 0) {
            return new R(new Exception("注册出错"));
        } else {
            return new R("result", "注册成功");
        }
    }

    /**
     * 物理删除用户
     *
     * @param guid
     * @return
     */
    @GetMapping("/deleteById")
    public R deleteById(String guid) {
        int code = userService.deleteById(guid);
        if (code == 0) {
            return new R(new Exception("永久删除出错"));
        }
        return new R("result", "永久删除成功");
    }

    /**
     * 逻辑删除用户,可批量删除
     *
     * @param guids
     * @return
     */
    @GetMapping("/delete")
    public R delete(String guids) {
        int code = userService.delete(guids);
        if (code == 0) {
            return new R(new Exception("删除出错"));
        }
        return new R("result", "删除成功");

    }

    /**
     * 重新启用用户
     *
     * @param guid
     * @return
     */
    @GetMapping("/restart")
    public R restart(String guid) {
        int code = userService.restart(guid);
        if (code == 0) {
            return new R(new Exception("启用出错"));
        }
        return new R("result", "启用成功");
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public R updateUser(@RequestBody UserMessage user) {
        String pwd = user.getPassword();
        //获取姓名首字母
        if (StringUtil.isNotBlank(user.getXm())) {
            String firstLetter = ChineseToFirstLetterUtil.ChineseToFirstLetter(user.getXm().trim());
            user.setFirstLetter(firstLetter);
        }
        int code = userService.updateUser(user);
        if (code == 0) {
            return new R(new Exception("修改出错"));
        } else {
            return new R("result", "修改成功");
        }
    }


    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/selectUserMessage")
    public R selectUserMessage(String userId) {
        UserMessage userMessage = userService.selectUserMessage(userId);

        if (userMessage != null) {
            String url="";
            try {
                if (userMessage.getAvator() == null || userMessage.getAvator().isEmpty()) {
                    url = "";
                } else {
                    url = template.getObjectURL(BUCKETNAME, userMessage.getAvator(), 604800);
                }
            } catch (Exception e) {
                logger.info("获取头像url失败！");
            } finally {
                //包装类返回
                PackUser packUser = new PackUser();
                packUser.setUrl(url);
                packUser.setUserMessage(userMessage);
                return new R(packUser);
            }


        } else {
            return new R(new Error("用户不存在"));
        }
    }

    /**
     * 使用id查询所有所在流程岗位
     *
     * @param userId
     * @return
     */
    @GetMapping("/selectFlowByUserId")
    public R selectFlowByUserId(String userId) {
        List list = flowPositionService.selectFlowByUserId(userId);
        return new R(list);
    }

    /**
     * 根据用户登录名查询用户信息
     *
     * @param loginName
     * @return
     */
    @GetMapping("/selectByLoginName")
    public R selectUser(String loginName) {
        UserMessage userMessage = userService.selectUser(loginName);
        return new R(userMessage);
    }

    /**
     * TODO 后期需要放入到用户认证组件
     * gen根据用户名，增加错误次数
     *
     * @param loginName
     * @return
     */
    @PostMapping("/increasePasswordByLoginName")
    public R increasePasswordByLoginName(String loginName) {


        return new R<>();
    }

    /**
     * 根据登录名查询用户信息
     *
     * @return 执行结果
     */
    @GetMapping("selectUserInfo")
    public R selectUserInfo() throws Exception {
        // 查询用户信息

        User userInfo = SecurityUtils.getLoginUserInfo();

        UserMessageVo user = null;
        if (userInfo != null) {
            user = userService.selectByUserName(userInfo.getUsername());
        }

        Map<String, Object> result = new HashMap();

        if (user != null) {
            result = CommonUtils.objectToMap(user);
            //封装直属机构信息
            Organization org = organizationService.findUserDirectOrg(user.getGuid());
            if (org != null) {
                result.put("org", org);

            }
            //封装用户组信息
            List list = userGroupService.selectUserGroup(user.getGuid());
            result.put("userGroup", list);

            //封装头像url
            String url = "";
            try {
                if (user.getAvator() == null || user.getAvator().isEmpty()) {
                    url = "";
                } else {
                    url = template.getObjectURL(BUCKETNAME, user.getAvator(), 604800);
                }
            }catch (Exception e){
                logger.info("拼装头像url失败！");
            }finally {
                result.put("avatorUrl", url);
            }
        }

        return new R<>(result);
    }

    /**
     * 上传头像
     *
     * @param file
     * @param objectName
     * @return
     */
    @SneakyThrows
    @PostMapping("/upload")
    public R createObject(MultipartFile file, String objectName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        objectName = objectName.replace(".", "_" + sdf.format(new Date()) + ".");
        template.putObject(BUCKETNAME, objectName, file.getInputStream(), file.getSize(), file.getContentType());
        Map map = new HashMap();
        map.put("url", template.getObjectURL(BUCKETNAME, objectName, 604800));
        map.put("objectName", objectName);

        return new R(map);
    }

    /**
     * 根据类型用户名分页查询用户信息
     *
     * @param type        类型：1用户、2岗位、3机构
     * @param name        根据类型分别为用户名、岗位名和机构名
     * @param currentPage 当前页
     * @param pageSize    每页记录数
     * @return
     */
    @GetMapping("selectUserForPage")
    public R selectUserForPage(String type, String name, int currentPage, int pageSize) {

        PageHelper.startPage(currentPage, pageSize);
        List<UserMessage> list = userService.selectUserForPage(type, name);
        PageInfo<UserMessage> pageInfo = new PageInfo<>(list);
        return new R<>(pageInfo);
    }

    /**
     * 根据姓名、姓名首字母和电话查询用户信息
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @GetMapping("selectUserByNamePhone")
    public R selectUserByNamePhone(String queryParam) {
        List<UserMessageVo> list = userService.selectUserByNamePhone(queryParam);
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
     * 根据姓名、姓名首字母查询用户信息
     * @param queryParam 查询参数可能为姓名、姓名首字母或电话
     * @return
     */
    @GetMapping("selectUserByName")
    public R selectUserByName(String xm) {
        List<UserMessageVo> list = userService.selectUserByNamePhone(xm);
        String url = "";
        for (UserMessageVo userMessage : list) {
            //获取头像url
            try {
                if (userMessage.getAvator() != null && !userMessage.getAvator().isEmpty()) {
                    url = template.getObjectURL(BUCKETNAME, userMessage.getAvator(), 604800);
                } else {
                    url = "";
                }
            }catch (Exception e){
                logger.info("拼装头像url失败！");
            }finally {
                userMessage.setPhotoUrl(url);
            }


        }
        return new R<>(list);
    }

    /**
     * 根据用户名修改用户首次登陆信息（1是首次登陆，0不是首次登陆）
     *
     * @param userName 用户登录名
     * @return
     */
    @GetMapping("updUserFirstLogin")
    public R updUserFirstLogin(String userName) {
        return new R<>(userService.updUserFirstLogin(userName));
    }

    /**
     * 根据机构ID查询同部门人员信息
     *
     * @param orgId
     * @return
     */
    @GetMapping("selectUserByOrgId")
    public R selectUserByNodeId(@RequestParam(value = "orgId", required = true) String orgId) {
        List<Map<String,Object>> resultList = new ArrayList<>();
        Map<String,Object> map;
        List<UserMessageVo> childrenList;
        List<String> idList;
        Boolean isExist;
        String url = "";
        List<UserMessageVo> list = organizationService.selectUserByNodeId(orgId);
        for (UserMessageVo userMessage : list) {
            //判断结果集中是否已存在该用户首字母
            map = new HashMap<>();
            childrenList = new ArrayList<>();
            idList = new ArrayList<>();
            isExist = false;
            for (Map<String, Object> resultMap : resultList) {
                if (userMessage.getFirstLetter().substring(0,1).equals(resultMap.get("firstLetter").toString().substring(0,1))) {
                    isExist = true;
                    break;
                }
            }
            //不存在则添加
            if (!isExist) {
                for (UserMessageVo user : list) {
                    //将同姓氏用户放到一个集合中
                    if (userMessage.getXm().substring(0,1).equals(user.getXm().substring(0,1))) {
                        //获取头像url
                        try {
                            if (user.getAvator() != null && !user.getAvator().isEmpty()) {
                                url = template.getObjectURL(BUCKETNAME, user.getAvator(),604800);
                            } else {
                                url = "";
                            }
                        } catch (Exception e) {
                            logger.info("拼装头像url失败！"+e);
                        } finally {
                            user.setPhotoUrl(url);
                        }
                        //放到children集合中
                        childrenList.add(user);
                        idList.add(userMessage.getGuid());
                    }
                }
                map.put("firstLetter", userMessage.getFirstLetter().substring(0,1));
                map.put("children", childrenList);
                //放到结果集中
                resultList.add(map);
            }
        }
        return new R<>(resultList);
    }
}
