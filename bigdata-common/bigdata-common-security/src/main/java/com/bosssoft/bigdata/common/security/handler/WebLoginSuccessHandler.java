/*
 *    Copyright (c) 2019-2025, bosssoft All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the bosssoft.vip developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: bosssoft (bosssoft@gmail.com)
 */

package com.bosssoft.bigdata.common.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Lucky
 * @date: 2019/04/02
 * <p>
 * 自定义登录成功页面
 * 页面登录成功，返回oauth token
 */
@Component
@Slf4j
public class WebLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		//Authentication接口封装认证信息
		log.info("登录成功");
		response.setContentType("application/json;charset=UTF-8");
		//将authentication认证信息转换为json格式的字符串写到response里面去
		String json = objectMapper.writeValueAsString(authentication);
		log.info("认证 authentication={} " + json);
		String html = "{\"code\":0,\"msg\":\"登录跳转成功\",\"data\": null}";
		response.getWriter().write(html);
	}
}