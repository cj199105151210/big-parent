## 当前版本
V1.0.0

**快速部署bigdata，请完全参考本篇文档。**  
如果有个性化的修改，请参考本篇运行起来以后，自行修改。循序渐进
- 前端 http://localhost:8080
- 认证 http://localhost:3000/token/login  admin/123456
- 监控 http://localhost:5001
- 文档 http://bigdata-gateway:2999/swagger-ui.html 【oauth2跨域问题，添加hosts】

### 特别说明
- flushdb 清空redis 
- 确保你的IDEA 已经安装lombok
- 代码git clone 
- 建议使用 IDEA 2018+ 启动效果会更好
- 内存较小开发机 每个微服务建议设置  -Xms128m -Xmx256m

### 环境说明
- jdk 1.8
- mysql 5.7
- redis 3.2+
- node 8.0+
- npm 6.0+
- zookeeper 3.4+


### 一、项目下载

```
git https://bosssoft/bos/bigdata.git
```

### 二、配置数据库
- 参数说明
```
版本： mysql5.6~7+
默认字符集: utf8mb4
默认排序规则: utf8mb4_general_ci
```

- 脚本说明
2.0 把核心库和业务库分开，建议使用两个库

```sql
1scheme.sql    建库语句
2bigdata.sql     核心数据库
```


### 三、bos配置修改   （建议批量替换）

- redis 修改  
bigdata/bigdata-config/src/main/resources/config/application-dev.yml

```
# redis 相关
spring:
  redis:
    password:
    host: bigdata-redis
```
- 数据库配置  
bigdata/bigdata-config/src/main/resources/config/bigdata-auth-dev.yml  
bigdata/bigdata-config/src/main/resources/config/bigdata-upms-dev.yml  
bigdata/bigdata-config/src/main/resources/config/bigdata-codegen-dev.yml  
bigdata/bigdata-config/src/main/resources/config/bigdata-daemon-dev.yml  
bigdata/bigdata-config/src/main/resources/config/bigdata-activiti-dev.yml

```
# 数据源
spring:
  datasource:
    username: root
    password: XXXX
    url: jdbc:mysql://bigdata-mysql:3306/bigdata?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai
```

### 四、配置本地hosts 建议使用 switchhost）

修改本地hosts文件中存在以下片段


```
# 本地测试环境
127.0.0.1	bigdata-eureka
127.0.0.1   bigdata-mysql
127.0.0.1	bigdata-zookeeper
127.0.0.1	bigdata-redis
127.0.0.1	bigdata-gateway
127.0.0.1	bigdata-minio
```
### 五、启动顺序（基础模块）
```text
1. BosxEurekaApplication   
2. BosxConfigApplication  
3. BosxGatewayApplication  
4. BosxAuthApplication 
5. BosxAdminApplication  
```

### 六、启动前端

```
git clone https://BOSX/bos/bigdata-ui.git

# 安装cnpm 镜像
npm run pre

# 安装依赖
npm install

# 启动
npm run dev
```


