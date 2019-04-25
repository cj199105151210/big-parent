package com.bosssoft.bigdata.user.center.config;

import com.bosssoft.bigdata.common.core.constant.BosxCachePrefixConstants;
import com.bosssoft.bigdata.common.data.mybatis.interceptor.DictIntercept;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fanghuaizheng
 * @Description:
 * @date 2019-04-01 17:38
 */
@Configuration
public class Config {

    @Autowired
    CacheManager cacheManager;

    @Bean
    public String addInteger(SqlSessionFactory sessionFactory){
        DictIntercept intercept = new DictIntercept();
        intercept.setCacheManager(cacheManager);
        intercept.setCACHE_PREFIX(BosxCachePrefixConstants.DICT);
        sessionFactory.getConfiguration().addInterceptor(intercept);
        return "11";
    }
}
