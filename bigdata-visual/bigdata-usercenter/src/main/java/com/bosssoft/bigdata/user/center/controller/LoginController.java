/**
 * Copyright (C), 2015-2019, 福建博思
 * FileName: LoginController
 * Author:   Jack Ma
 * Date:     2019/3/6 15:30
 * Description: 用户登录controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.controller;


import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.user.center.model.ValidateCode;
import com.bosssoft.bigdata.user.center.service.UserService;
import com.bosssoft.bigdata.usercenter.api.entity.UserMessage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("login")
public class LoginController {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public UserService userService;

    /**
     * 获取验证码测试方法
     * @param userName
     * @return
     */
    @PostMapping("/test")
    public R test(String userName) {
        String result = userName+"success";
        return new R<>(result);
    }

    /**
     * 根据用户名修改用户密码
     * @param loginName 登录名
     * @param password 密码
     * @return 执行结果
     */
    @PostMapping("updatePassword")
    public R updatePassword (@RequestParam(value = "userName",required = true)String loginName
                                        ,@RequestParam(value = "password",required = true)String password) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 查询用户信息
        UserMessage user = userService.selectByLoginName(loginName);
        if (user != null) {
            // 使用PasswordEncoder加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String word = passwordEncoder.encode(password);
            // 修改密码
            user.setPassword(word);
            userService.updatePassword(user);
            resultMap.put("result","success");
        } else {
            resultMap.put("result","该用户不存在！");
        }
        return new R<>(resultMap);
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode vCode = new ValidateCode(100,30,4,60);
        HttpSession session = request.getSession();
        session.removeAttribute("validateCode");
        vCode.write(response.getOutputStream());
        session.setAttribute("strCode", vCode.getCode());
    }

    /**
     * 校验验证码
     * @param strCode
     * @param session
     * @return
     */
    @PostMapping("checkCode")
    public R checkCode(String strCode, HttpSession session){

        String code = session.getAttribute("strCode").toString();
        Boolean  flag = null;
        if(StringUtils.equalsIgnoreCase(strCode,code)){
            flag = true;
        }else{
            flag = false;
        }
        return new R<>(flag);
    }

    /**
     * 校验用户名是否存在
     * @param userName
     * @return
     */
    @GetMapping("checkUserName")
    public R checkUserName(String userName) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        UserMessage user = userService.selectByLoginName(userName);

        if (user == null) {
            resultMap.put("result","FALSE");
        } else {
            resultMap.put("result","SUCCESS");
        }
        return new R<>(resultMap);
    }

    /**
     * 根据登录名查询用户信息
     * @param loginName 登录名
     * @return 执行结果
     */
    @GetMapping("selectByLoginName")
    public R selectByLoginName (String loginName) {
        // 查询用户信息
        UserMessage user = userService.selectByLoginName(loginName);
        return new R<>(user);
    }
}
