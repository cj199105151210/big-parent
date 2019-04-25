package com.bosssoft.bigdata.common.security.component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author lucky
 * @date 2019/2/22
 * <p>
 * 1. 支持remoteTokenServices 负载均衡
 * 2. 支持 获取用户全部信息
 */
@Slf4j
public class BosxResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
	@Autowired
	protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;
	@Autowired
	protected RemoteTokenServices remoteTokenServices;
	//@Autowired
	//protected UserDetailsService userDetailsService;
	@Autowired
	private PermitAllUrlProperties permitAllUrlProperties;
	@Autowired
	private RestTemplate lbRestTemplate;
	//@Setter
	//private boolean details;

	/**
	 * 默认的配置，对外暴露
	 *
	 * @param httpSecurity
	 */
	@Override
	@SneakyThrows
	public void configure(HttpSecurity httpSecurity) {
		//允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
		httpSecurity.headers().frameOptions().disable();
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>
				.ExpressionInterceptUrlRegistry registry = httpSecurity
				.authorizeRequests();
		permitAllUrlProperties.getIgnoreUrls()
				.forEach(url -> registry.antMatchers(url).permitAll());
		registry.anyRequest().authenticated()
				.and().csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		UserAuthenticationConverter userTokenConverter = new BosxUserAuthenticationConverter();
		accessTokenConverter.setUserTokenConverter(userTokenConverter);

		remoteTokenServices.setRestTemplate(lbRestTemplate);
		remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
		resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
				.tokenServices(remoteTokenServices);
	}



//	/**
//	 * 提供子类重写
//	 * <p>
//	 * 1. 不重写，默认支持获取用户名
//	 * 2. 重写notGetUser，提供性能
//	 * <p>
//	 * see codegen ResourceServerConfigurer
//	 *
//	 * @param resources
//	 */
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) {
//		if (details) {
//			canGetUser(resources);
//		} else {
//			notGetUser(resources);
//		}
//	}
//
//
//	/**
//	 * 不获取用户详细 只有用户名
//	 *
//	 * @param resources
//	 */
//	protected void notGetUser(ResourceServerSecurityConfigurer resources) {
//		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//		DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
//		accessTokenConverter.setUserTokenConverter(userTokenConverter);
//
//		remoteTokenServices.setRestTemplate(lbRestTemplate);
//		remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
//		resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
//				.tokenServices(remoteTokenServices);
//	}
//
//
//	/**
//	 * 上下文中获取用户全部信息，两次调用userDetailsService，影响性能
//	 *
//	 * @param resources
//	 */
//	private void canGetUser(ResourceServerSecurityConfigurer resources) {
//		DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//		DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
//		userTokenConverter.setUserDetailsService(userDetailsService);
//		accessTokenConverter.setUserTokenConverter(userTokenConverter);
//
//		remoteTokenServices.setRestTemplate(lbRestTemplate);
//		remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
//		resources.authenticationEntryPoint(resourceAuthExceptionEntryPoint)
//				.tokenServices(remoteTokenServices);
//	}

}
