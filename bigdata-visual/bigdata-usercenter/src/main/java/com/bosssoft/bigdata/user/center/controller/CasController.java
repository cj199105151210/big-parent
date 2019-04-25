package com.bosssoft.bigdata.user.center.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author fanghuaizheng
 * @Description:单点登录控制器
 * @date 2019-03-12 17:00
 */
@RestController
public class CasController {
    @Value("${cas.server-url-prefix:http://localhost:9090/cas}")
    private String serverUrlPrefix;

    @Value("${cas.client-host-url:http://localhost:9100}")
    private String clientHostUrl;

    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpSession session) {
        session.invalidate();
        //使用cas退出成功后,跳转到http://cas.client1.com:9001/logout/success
        return "redirect:"+serverUrlPrefix+"/logout?service="+clientHostUrl;
    }
}
