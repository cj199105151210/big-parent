package com.bosssoft.bigdata.admin.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后端获取用户信息【URL仅供参考】
 * @author lucky
 * @date 2018/12/16
 */
@Slf4j
public class BackendToGetUserInfo {


    /**
     * 后端获取用户信息<br/>
     * 登陆完成后进入主页面，然后点击具体的菜单，这时候就会跟相关的微服务产生交互，在后端怎么获取到用户信息
     * 步骤：
     * 1.获取token 使用request.getHeader(HttpHeaders.AUTHORIZATION)
     * 2.使用token访问用户接口http://bigdata-gateway:2999/admin/user/info 带上 authorization: Bearer TOKEN信息
     * @param request
     * @return
     */
    @GetMapping("/backendToGetUserInfo")
    public String backendToGetUserInfo(HttpServletRequest request) {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        String url = "http://bigdata-gateway:2999/admin/user/info";
        String result = HttpRequest.get(url)
                .header("accept", "*/*")
                .header("authorization", "Bearer " + bearer)
                .execute().body();
        return result;
    }

//    public static void main(String[] args) {
//
//
//
//    }
//    public static void testHutoolGet() {
//        String getResult = HttpUtil
//                .createGet("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=15677386236")
//                .execute()
//                .charset("UTF-8")
//                .body();
//        log.info("getResult:"+getResult);
//    }
//    public static void testHutoolPost(String cameraId) {
//        JSONObject jsonObject = JSONUtil.createObj();
//        jsonObject.put("cameraId", cameraId);
//        jsonObject.put("startTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        jsonObject.put("callback", "http://www.baidu.com");
//        String postResult = HttpRequest
//                .post("http://localhost:8080/v1/platedetect/tasks")
//                .header("Content-Type","application/json")
//                .body(jsonObject)
//                .execute()
//                .body();
//        log.info("postResult:"+postResult);
//    }
//
//
//		/*
//		<!--引入包安全模块-->
//        <dependency>
//            <groupId>com.bosssoft.bigdata</groupId>
//            <artifactId>bigdata-common-security</artifactId>
//            <version>1.0.1</version>
//        </dependency>
//		<!--工具类核心包-->
//		<dependency>
//			<groupId>com.bosssoft.bigdata</groupId>
//			<artifactId>bigdata-common-core</artifactId>
//			<version>${bigdata.version}</version>
//		</dependency>
//        */
//    /**
//     * 登陆完成后，进入主页面，然后点击具体的菜单，这时候就会跟住房维修基金的微服务产生交互，那我在后端怎么获取到用户信息
//     * 步骤：
//     * 1.获取token 使用request.getHeader(HttpHeaders.AUTHORIZATION)
//     * 2.使用token访问用户接口http://bigdata-gateway:2999/admin/user/info 带上 authorization: Bearer TOKEN信息
//     * @param request
//     * @return
//     */
//    @GetMapping("/getHeaderBearer")
//    public String testGetUserInfo(HttpServletRequest request) {
//        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
//        String url = "http://bigdata-gateway:2999/admin/user/info";
//        String result = HttpRequest.get(url)
//                .header("accept", "*/*")
//                .header("authorization", "Bearer " + bearer)
//                .execute().body();
//        return result;
//    }
//		/*
//		<!--引入包安全模块-->
//        <dependency>
//            <groupId>com.bosssoft.bigdata</groupId>
//            <artifactId>bigdata-common-security</artifactId>
//            <version>1.0.1</version>
//        </dependency>
//        */
//    /**
//     * 登陆完成后，进入主页面，然后点击具体的菜单，这时候就会跟住房维修基金的微服务产生交互，那我在后端怎么获取到用户信息
//     * 步骤：
//     * 1.获取token 使用request.getHeader(HttpHeaders.AUTHORIZATION)
//     * 2.使用token访问用户接口http://bigdata-gateway:2999/admin/user/info 带上 authorization: Bearer TOKEN信息
//     * @param request
//     * @return
//     */
//    @GetMapping("/getHeaderBearer")
//    public String onAuthenticationSuccess(HttpServletRequest request) {
//
//        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
//        String result = "";
//        BufferedReader bufferedReader = null;
//        try {
//            URL realUrl = new URL("http://bigdata-gateway:2999/admin/user/info");
//
//            URLConnection connection = realUrl.openConnection();
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("authorization", "Bearer " + bearer);
//            connection.connect();
//            Map<String, List<String>> headerFields = connection.getHeaderFields();
//            for (String key : headerFields.keySet()) {
//                System.out.println("Response Head: " + key + " >>> " + headerFields.get(key));
//            }
//            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            while (null != (line = bufferedReader.readLine())) {
//                result += line;
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }
}
