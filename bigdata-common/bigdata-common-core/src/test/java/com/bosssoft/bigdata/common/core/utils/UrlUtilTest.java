package com.bosssoft.bigdata.common.core.utils;

import org.junit.Test;


public class UrlUtilTest {
    @Test
    public void encodeURL() {
        String urlStr = "http://localhost:8080/hello?name=jack";
        String url = UrlUtil.getPath(urlStr);
        System.out.println("获取url路径:{}" + url);
        String encodeUrl = UrlUtil.encodeURL(urlStr, Charsets.UTF_8);
        System.out.println(encodeUrl);
        String decodeURL = UrlUtil.decodeURL(encodeUrl, Charsets.UTF_8);
        System.out.println(decodeURL);
    }

}
