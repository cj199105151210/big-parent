-- boss 核心表(运维管理)
create database `bigdata` default character set utf8mb4 collate utf8mb4_general_ci;

-- boss 核心表(聚合平台)
create database `bigdata_aggregation` default character set utf8mb4 collate utf8mb4_general_ci;

-- boss 核心表(链路追踪)
create database `bigdata_zipkin` default character set utf8mb4 collate utf8mb4_general_ci;

-- boss 核心表(江西小程序聚合平台)
create database `bigdata_polymer` default character set utf8mb4 collate utf8mb4_general_ci;

-- boss 核心表(用户中心)
create database `bigdata_ucenter` default character set utf8mb4 collate utf8mb4_general_ci;

-- boss 核心表(分布式事务)
create database `bigdata_daemon` default character set utf8mb4 collate utf8mb4_general_ci;

-- boss 核心表(研究守护进程定时器)
create database `bigdata_job` default character set utf8mb4 collate utf8mb4_general_ci;

-- boss 核心表(研究代码生成等)
create database `bigdata_codegen` default character set utf8mb4 collate utf8mb4_general_ci;
