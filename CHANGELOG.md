更新日志
```lua
版本
├── 时间
├── 描述
├    └── 添加新特性
├    └── 重构代码
├    └── 升级依赖
├    └── 优化逻辑
├    └── 修复bug
```
## 1.0.1
### 2019-04-22

添加新特性:
- 新增分布式事务协调服务
- 新增特性.ignore-urls 直接通过Inner注解同步

    剔除各个微服务中添加繁琐配置ignore-urls
    //不允许接口开放访问,需要用户登录验证才可以访问
    @Inner(value = true)
    //允许接口开放访问
    @Inner(value = false)
    
重构代码:

- 重构自动降级

    减轻配置仅需要@EnableBosxResourceServer不用再添加(details = true)
    
    建少factory/fallback繁琐

升级依赖:
- 升级 spring-boot 2.1.3
- 升级 hutool 4.4.5
- 升级 minio 6.0.2

优化逻辑:

- 用于构建和推送Docker镜像的Maven插件，为后期考虑
- 后期配置表sys_dict_item
- 添加参数配置表初始化脚本

修复bug:
- 代码一致性，可读性
- 提示中有must be unique，检查pom文件可能有重复的dependency,将重复的去除即可

## 1.0.0
### 2019-04-09

添加新特性:

- 添加定时任务(Quartz)模块、数据库和初始化脚本
- 加入配置文件链路追踪、定时器配置、江西小程序 
- 添加运维平台，参数配置功能

重构代码:

- 重构字典表，调整规范字典表和字典项

优化逻辑:

- 聚合平台swgger接口Api value tags
- 添加初始化数据库脚本和后续升级表 
- swagger公共信息调整 
- 保存路由样式格式、安全常量、驼峰定义、创建时间

升级依赖:

- 升级 mybatis-plus 版本，从3.0.6到3.1.0 

问题修复 

- 填充安全常量进行规范可维护 
- 修复验证码发送过于频繁提示信息
- 调整参数配置数据库，int类型改成定义为char(1)
- 移除热加载导致不可预知的错误spring-boot-devtools. 
- 移除臃肿不使用到代码bpm\daemon\cas模块
- minio配置采用域名hosts配置,用户名称和密码定义使用
- 后续采用分支开发模式branch，mater进行统一合并merge
