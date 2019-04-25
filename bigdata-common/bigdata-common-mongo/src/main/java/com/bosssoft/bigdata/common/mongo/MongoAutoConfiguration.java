package com.bosssoft.bigdata.common.mongo;

import com.bosssoft.bigdata.common.mongo.service.MongoTemplate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @program: study-bigdata-mongo
 * @description: mongo 自动配置类
 * @author: Mr.Lucky
 * @create: 2019-03-08 16:45
 **/
@AllArgsConstructor
@EnableConfigurationProperties({MongoProperties.class})
public class MongoAutoConfiguration {
    private final MongoProperties mongoProperties;

    @Bean
    @ConditionalOnMissingBean(MongoTemplate.class)
    @ConditionalOnProperty(name = "mongo.url")
    MongoTemplate mongoTemplate() {
        return  new MongoTemplate(
                mongoProperties.getUrl(),
                mongoProperties.getPort(),
                mongoProperties.getDbName(),
                mongoProperties.getAccessKey(),
                mongoProperties.getSecretKey());
    }

}
