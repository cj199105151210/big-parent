package com.bosssoft.bigdata.common.transaction;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: bigdata
 * @Description:事务配置
 * @Date: Created in 17:05 2019/3/10
 * @Modified By:
 * @since 4.1.0
 */
@Configuration
@ComponentScan({"com.codingapi.tx", "com.bosssoft.bigdata.common.transaction"})
public class TransactionConfiguration {
}
