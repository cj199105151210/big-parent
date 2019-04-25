/**
 * Copyright (C), 2019, 福建博思
 * FileName: CasDataSourceConfig
 * Author:   mapengpeng
 * Date:     2019/4/15 10:31
 * Description: Cas数据源配置
 * version:  1.0
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.bosssoft.bigdata.user.center.mapper.casMapper", sqlSessionTemplateRef =
        "casSqlSessionTemplate")
public class CasDataSourceConfig {


    @Bean(name = "casDataSource")
    @Qualifier("casDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cas-datasource")
    public DataSource casDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("casJdbcTemplate")
    @Qualifier("casJdbcTemplate")
    public JdbcTemplate casJdbcTemplate(@Qualifier("casDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "casSqlSessionFactory")
    @Qualifier("casSqlSessionFactory")
    public SqlSessionFactory casSqlSessionFactory(@Qualifier("casDataSource") DataSource dataSource) throws
            Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "casSqlSessionTemplate")
    public SqlSessionTemplate casSqlSessionTemplate(@Qualifier("casSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
