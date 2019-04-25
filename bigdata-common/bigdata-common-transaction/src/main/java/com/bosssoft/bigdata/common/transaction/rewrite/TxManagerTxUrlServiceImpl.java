package com.bosssoft.bigdata.common.transaction.rewrite;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import com.bosssoft.bigdata.common.core.constant.ServiceNameConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

/**
 * @Author: bigdata
 * @Description:使用服务发现重写 Txmanager 获取规则
 * @Date: Created in 17:05 2019/3/10
 * @Modified By:
 */
@Slf4j
@Service
@AllArgsConstructor
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {
	private final LoadBalancerClient loadBalancerClient;

	@Override
	public String getTxUrl() {
		ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceNameConstants.TX_MANAGER);
		String host = serviceInstance.getHost();
		Integer port = serviceInstance.getPort();
		String url = String.format("http://%s:%s/tx/manager/", host, port);
		log.info("tm.manager.url -> {}", url);
		return url;
	}
}
