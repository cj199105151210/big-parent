package com.bosssoft.bigdata.common.data.tenant;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 多租户配置
 *
 * @author oathsign
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "bosssoft.tenant")
public class BosxTenantConfigProperties {

	/**
	 * 维护租户列名称
	 */
	private String column="tenant_id";

	/**
	 * 多租户的数据表集合
	 */
	private List<String> tables = new ArrayList<>();
}
