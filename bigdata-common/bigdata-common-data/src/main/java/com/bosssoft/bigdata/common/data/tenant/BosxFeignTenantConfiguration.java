package com.bosssoft.bigdata.common.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lucky
 * @date 2019/2/18
 * feign 租户信息拦截
 */
@Configuration
public class BosxFeignTenantConfiguration {
	@Bean
	public RequestInterceptor bosxFeignTenantInterceptor() {
		return new BosxFeignTenantInterceptor();
	}
}
