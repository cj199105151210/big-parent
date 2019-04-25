package com.bosssoft.bigdata.common.mongo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: study-bigdata-mongo
 * @description: mongo 配置信息
 * @author: Mr.Lucky
 * @create: 2019-03-08 16:45
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "mongo")
public class MongoProperties {
    /**
     * mongo 服务地址
     */
    private String url;
    /**
     * mongo 服务端口
     */
    private int port;
    /**
     * mongo 数据库
     */
    private String dbName;
    /**
     * mongo 用户名
     */
    private String accessKey;

    /**
     * mongo 密码
     */
    private String secretKey;
}
