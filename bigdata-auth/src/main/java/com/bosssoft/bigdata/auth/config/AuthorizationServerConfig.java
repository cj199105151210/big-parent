package com.bosssoft.bigdata.auth.config;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import com.bosssoft.bigdata.common.core.constant.SecurityConstants;
import com.bosssoft.bigdata.common.data.tenant.TenantContextHolder;
import com.bosssoft.bigdata.common.security.component.BosxWebResponseExceptionTranslator;
import com.bosssoft.bigdata.common.security.service.BosxClientDetailsService;
import com.bosssoft.bigdata.common.security.service.BosxUser;
import com.bosssoft.bigdata.common.security.service.BosxUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author: Lucky
 * @Description:认证服务器配置
 * @Date: Created in 23:30 2019/2/23
 * @Modified By:
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	private final DataSource dataSource;
	private final BosxUserDetailsService bosxUserDetailsService;
	private final AuthenticationManager authenticationManagerBean;
	private final RedisConnectionFactory redisConnectionFactory;

	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		BosxClientDetailsService clientDetailsService = new BosxClientDetailsService(dataSource);
		clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
		clients.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer
				.allowFormAuthenticationForClients()
				.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.tokenStore(tokenStore())
				.tokenEnhancer(tokenEnhancer())
				.userDetailsService(bosxUserDetailsService)
				.authenticationManager(authenticationManagerBean)
				.reuseRefreshTokens(false)
				.exceptionTranslator(new BosxWebResponseExceptionTranslator());
	}


	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(SecurityConstants.BOSX_PREFIX + SecurityConstants.OAUTH_PREFIX);
		tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
			@Override
			public String extractKey(OAuth2Authentication authentication) {
				return super.extractKey(authentication) + ":" + TenantContextHolder.getTenantId();
			}
		});
		return tokenStore;
	}

	/**
	 * token增强，客户端模式不增强。
	 *
	 * @return TokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return (accessToken, authentication) -> {
			if (SecurityConstants.CLIENT_CREDENTIALS
					.equals(authentication.getOAuth2Request().getGrantType())) {
				return accessToken;
			}

			final Map<String, Object> additionalInfo = new HashMap<>(8);
//			BosxUser bosxUser = (BosxUser) authentication.getUserAuthentication().getPrincipal();
			Object principal = authentication.getUserAuthentication().getPrincipal();
//			additionalInfo.put("user_id", bosxUser.getId());
//			additionalInfo.put("username", bosxUser.getUsername());
//			additionalInfo.put("dept_id", bosxUser.getDeptId());
//			additionalInfo.put("tenant_id", bosxUser.getTenantId());
//			additionalInfo.put("license", SecurityConstants.BOSX_LICENSE);
			try {
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(obj2Map(principal));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return accessToken;
		};
	}

	private   Map<String,Object> obj2Map(Object obj) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}
}
