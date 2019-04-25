package com.bosssoft.bigdata.manager.manager.service;

import com.bosssoft.bigdata.manager.model.LoadBalanceInfo;

/**
 * @author LCN on 2019/12/5
 */
public interface LoadBalanceService {

	boolean put(LoadBalanceInfo loadBalanceInfo);

	LoadBalanceInfo get(String groupId, String key);

	boolean remove(String groupId);

	String getLoadBalanceGroupName(String groupId);

}
