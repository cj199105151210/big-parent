package com.bosssoft.bigdata.auth.config;

import com.bosssoft.bigdata.auth.handler.BosxSimpleUrlAuthenticationFailureHandler;
import com.bosssoft.bigdata.common.security.component.BosxWebResponseExceptionTranslator;
import com.bosssoft.bigdata.common.security.handler.MobileLoginSuccessHandler;
import com.bosssoft.bigdata.common.security.mobile.MobileSecurityConfigurer;
import com.bosssoft.bigdata.common.security.mobilePasswordLogin.PasswordAuthenticationSecurityConfig;
import com.bosssoft.bigdata.common.security.service.BosxUserDetailsService;
import com.bosssoft.bigdata.usercenter.api.feign.RemoteUserLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @Author: Lucky
 * @Description:认证相关配置
 * @Date: Created in 23:30 2019/2/23
 * @Modified By:
 */
@Primary
@Order(90)
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private BosxUserDetailsService userDetailsService;
	@Lazy
	@Autowired
	private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;

	@Autowired
	BosxSimpleUrlAuthenticationFailureHandler bosxSimpleUrlAuthenticationFailureHandler;

//	@Autowired
//	RemoteUserLogService remoteUserLogService;

	@Override
	@SneakyThrows
	protected void configure(HttpSecurity http) {
		http
			.formLogin()
			.loginPage("/token/login")
			.loginProcessingUrl("/token/form")
			.and()
			.authorizeRequests()
			.antMatchers(
				"/token/**",
				"/actuator/**",
				"/mobile/password").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.apply(mobileSecurityConfigurer());
	}

	/**
	 * 不拦截静态资源 TODO
	 *
	 * @param web
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**");
	}

	@Bean
	@Override
	@SneakyThrows
	public AuthenticationManager authenticationManagerBean() {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationSuccessHandler mobileLoginSuccessHandler() {
		return MobileLoginSuccessHandler.builder()
			.objectMapper(objectMapper)
			.clientDetailsService(clientDetailsService)
			.passwordEncoder(passwordEncoder())
//				.remoteUserLogService(remoteUserLogService)
			.defaultAuthorizationServerTokenServices(defaultAuthorizationServerTokenServices).build();
	}




	@Bean
	public PasswordAuthenticationSecurityConfig mobileSecurityConfigurer() {
//		MobileSecurityConfigurer mobileSecurityConfigurer = new MobileSecurityConfigurer();
//		mobileSecurityConfigurer.setMobileLoginSuccessHandler(mobileLoginSuccessHandler());
//		mobileSecurityConfigurer.setUserDetailsService(userDetailsService);
//		return mobileSecurityConfigurer;

		PasswordAuthenticationSecurityConfig passwordAuthenticationSecurityConfig = new PasswordAuthenticationSecurityConfig();
		passwordAuthenticationSecurityConfig.setAuthenticationSuccessHandler(mobileLoginSuccessHandler());
		passwordAuthenticationSecurityConfig.setBosxUserDetailsService(userDetailsService);
		passwordAuthenticationSecurityConfig.setAuthenticationFailureHandler(bosxSimpleUrlAuthenticationFailureHandler);

		return passwordAuthenticationSecurityConfig;



	}


	/**
	 * https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-storage-updated
	 * Encoded password does not look like BCrypt
	 *
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
