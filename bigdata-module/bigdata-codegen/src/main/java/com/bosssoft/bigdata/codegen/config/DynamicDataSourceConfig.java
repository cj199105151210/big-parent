package com.bosssoft.bigdata.codegen.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author lucky
 * @date 2019-03-31
 * <p>
 * 动态数据源切换配置
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicDataSourceConfig implements TransactionManagementConfigurer {
	private final Map<Object, Object> dataSourceMap = new HashMap<>(8);
	private final DataSourceProperties dataSourceProperties;

	@Bean("dynamicDataSource")
	public com.bosssoft.bigdata.codegen.config.DynamicDataSource dataSource() {
		com.bosssoft.bigdata.codegen.config.DynamicDataSource ds = new com.bosssoft.bigdata.codegen.config.DynamicDataSource();
		HikariDataSource cads = new HikariDataSource();
		cads.setJdbcUrl(dataSourceProperties.getUrl());
		cads.setDriverClassName(dataSourceProperties.getDriverClassName());
		cads.setUsername(dataSourceProperties.getUsername());
		cads.setPassword(dataSourceProperties.getPassword());
		ds.setDefaultTargetDataSource(cads);
		dataSourceMap.put(0, cads);
		ds.setTargetDataSources(dataSourceMap);
		return ds;
	}

	/**
	 * 组装默认配置的数据源，查询数据库配置
	 */
	@PostConstruct
	public void init() {
		DriverManagerDataSource dds = new DriverManagerDataSource();
		dds.setUrl(dataSourceProperties.getUrl());
		dds.setDriverClassName(dataSourceProperties.getDriverClassName());
		dds.setUsername(dataSourceProperties.getUsername());
		dds.setPassword(dataSourceProperties.getPassword());

		String sql = "select * from sys_datasource_conf where del_flag = '0' ";
		List<Map<String, Object>> dbList = new JdbcTemplate(dds).queryForList(sql);
		log.info("开始 -> 初始化动态数据源");
		Optional.of(dbList).ifPresent(list -> list.forEach(db -> {
			log.info("数据源:{}", db.get("name"));
			HikariDataSource ds = new HikariDataSource();
			ds.setJdbcUrl(String.valueOf(db.get("url")));
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds.setUsername(String.valueOf(db.get("username")));
			ds.setPassword(String.valueOf(db.get("password")));
			dataSourceMap.put(db.get("id"), ds);
		}));

		log.info("完毕 -> 初始化动态数据源,共计 {} 条", dataSourceMap.size());
	}

	/**
	 * 重新加载数据源配置
	 */
	public void reload() {
		init();
		com.bosssoft.bigdata.codegen.config.DynamicDataSource dataSource = dataSource();
		dataSource.setTargetDataSources(dataSourceMap);
		dataSource.afterPropertiesSet();
	}


	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}


}