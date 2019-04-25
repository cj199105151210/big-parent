package com.bosssoft.bigdata.common.security.feign;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * fegin 配置增强 
 * @date 2019/2/23
 * @author lucky
 */
@Configuration
@ConditionalOnClass(Feign.class)
public class BosxFeignConfiguration {

	@Bean
	@ConditionalOnProperty("security.oauth2.client.client-id")
	public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
															OAuth2ProtectedResourceDetails resource,
															AccessTokenContextRelay accessTokenContextRelay) {
		return new BosxFeignClientInterceptor(oAuth2ClientContext, resource, accessTokenContextRelay);
	}

}