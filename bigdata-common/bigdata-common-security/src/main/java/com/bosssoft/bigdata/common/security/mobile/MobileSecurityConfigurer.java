package com.bosssoft.bigdata.common.security.mobile;

import com.bosssoft.bigdata.common.security.component.ResourceAuthExceptionEntryPoint;
import com.bosssoft.bigdata.common.security.service.BosxUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author lucky
 * @date 2019/2/18
 * 手机号登录配置入口
 */
@Getter
@Setter
@Component
public class MobileSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private AuthenticationEventPublisher defaultAuthenticationEventPublisher;
	private AuthenticationSuccessHandler mobileLoginSuccessHandler;
	private BosxUserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) {
		MobileAuthenticationFilter mobileAuthenticationFilter = new MobileAuthenticationFilter();
		mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		mobileAuthenticationFilter.setAuthenticationSuccessHandler(mobileLoginSuccessHandler);
		mobileAuthenticationFilter.setEventPublisher(defaultAuthenticationEventPublisher);
		mobileAuthenticationFilter.setAuthenticationEntryPoint(new ResourceAuthExceptionEntryPoint(objectMapper));

		MobileAuthenticationProvider mobileAuthenticationProvider = new MobileAuthenticationProvider();
		mobileAuthenticationProvider.setUserDetailsService(userDetailsService);
		http.authenticationProvider(mobileAuthenticationProvider)
			.addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
