# 添加模块

## 1. 数据库添加
INSERT INTO `bigdata`.`sys_route_conf`(`id`, `route_name`, `route_id`, `predicates`, `filters`, `uri`, `order`, `create_time`, `update_time`, `del_flag`) VALUES (21, '聚合平台模块', 'bigdata-aggregation', '[{\"args\":{\"_genkey_0\":\"/aggregation/**\"},\"name\":\"Path\"}]', '[]', 'lb://bigdata-aggregation', 0, '2019-03-02 23:03:55', '2019-03-05 19:27:13', '0');

## 2. 配置中心
bigdata-config/src/main/resources/config
添加bigdata-aggregation-dev.yml文件
```yml
## spring security 配置
security:
  oauth2:
    client:
      client-id: ENC(3ZLPfPwewUvP2/zuTY411A==)      # bos
      client-secret: ENC(3ZLPfPwewUvP2/zuTY411A==)  # bos
      scope: server
      # 默认放行url,子模块重写时application-dev.yml中的公共配置会被覆盖,所以要把公共配置中的放行url再写一次
      ignore-urls:
        - /actuator/**
        - /v2/api-docs
        - /mobile/**  #暂时放行

# 数据源
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: bsxc!@#123
    # 数据库后期改成微服务库： bigdata_aggregation
    url: jdbc:mysql://bigdata-mysql:3306/bigdata?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true

```

## 3. 模块添加(格式仅供参考，不合理可调整)
- 公共api
```lua
com
├── bosssoft
├    ├── bigdata
├         ├── aggregation
├              ├── api
├                   ├── dto
├                   ├── entity
├                   ├── enums
├                   ├── feign
├                        ├── factory
├                        ├── fallback
├                   ├── vo
resources
├── META-INF
├    ├── spring.factories
```
- 平台manager
```lua
com
├── bosssoft
├    ├── bigdata
├         ├── aggregation
├              ├── config
├              ├── controller
├              ├── handler
├              ├── mapper
├              ├── service
├                   ├── impl
├              ├── util
├         ├── Bigdata模块名称Application
resources
├── mapper
bootstrap.yml
```