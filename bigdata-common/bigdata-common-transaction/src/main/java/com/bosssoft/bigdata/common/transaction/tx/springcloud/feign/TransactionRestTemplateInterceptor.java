package com.bosssoft.bigdata.common.transaction.tx.springcloud.feign;

import com.codingapi.tx.aop.bean.TxTransactionLocal;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: bigdata
 * @Description:事务休息模板拦截器
 * @Date: Created in 17:05 2019/3/10
 * @Modified By:
 * @since 4.1.0
 */
@Slf4j
public class TransactionRestTemplateInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {

		TxTransactionLocal txTransactionLocal = TxTransactionLocal.current();
		String groupId = txTransactionLocal == null ? null : txTransactionLocal.getGroupId();

		log.info("LCN-SpringCloud TxGroup info -> groupId:" + groupId);

		if (txTransactionLocal != null) {
			requestTemplate.header("tx-group", groupId);
		}
	}

}
