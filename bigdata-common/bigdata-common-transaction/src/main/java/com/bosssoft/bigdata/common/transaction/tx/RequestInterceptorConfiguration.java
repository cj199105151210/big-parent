package com.bosssoft.bigdata.common.transaction.tx;

import com.bosssoft.bigdata.common.transaction.tx.springcloud.feign.TransactionRestTemplateInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: bigdata
 * @Description:请求拦截器配置
 * @Date: Created in 17:05 2019/3/10
 * @Modified By:
 * @since 4.1.0
 */
@Configuration
public class RequestInterceptorConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor() {
		return new TransactionRestTemplateInterceptor();
	}
}
