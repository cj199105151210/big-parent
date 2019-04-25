package org.springframework.cloud.openfeign;

import feign.hystrix.HystrixFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author bigdata
 * <p>
 * HystrixFeignTargeter 配置
 */
@Configuration
@ConditionalOnClass(HystrixFeign.class)
@ConditionalOnProperty("feign.hystrix.enabled")
public class BosxHystrixFeignTargeterConfiguration {

	@Bean
	@Primary
	public Targeter bosxFeignTargeter() {
		return new BosxHystrixTargeter();
	}
}
