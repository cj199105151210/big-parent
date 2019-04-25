package com.bosssoft.bigdata.manager.manager.service.impl;

import com.bosssoft.bigdata.manager.config.ConfigReader;
import com.bosssoft.bigdata.manager.manager.service.LoadBalanceService;
import com.bosssoft.bigdata.manager.model.LoadBalanceInfo;
import com.bosssoft.bigdata.manager.redis.RedisServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2019/12/5
 */
@Service
public class LoadBalanceServiceImpl implements LoadBalanceService {

	@Autowired
	private RedisServerService redisServerService;

	@Autowired
	private ConfigReader configReader;

	@Override
	public boolean put(LoadBalanceInfo loadBalanceInfo) {
		String groupName = getLoadBalanceGroupName(loadBalanceInfo.getGroupId());
		redisServerService.saveLoadBalance(groupName, loadBalanceInfo.getKey(), loadBalanceInfo.getData());
		return true;
	}

	@Override
	public LoadBalanceInfo get(String groupId, String key) {
		String groupName = getLoadBalanceGroupName(groupId);
		String bytes = redisServerService.getLoadBalance(groupName, key);
		if (bytes == null) {
			return null;
		}

		LoadBalanceInfo loadBalanceInfo = new LoadBalanceInfo();
		loadBalanceInfo.setGroupId(groupId);
		loadBalanceInfo.setKey(key);
		loadBalanceInfo.setData(bytes);
		return loadBalanceInfo;
	}

	@Override
	public boolean remove(String groupId) {
		redisServerService.deleteKey(getLoadBalanceGroupName(groupId));
		return true;
	}

	@Override
	public String getLoadBalanceGroupName(String groupId) {
		return configReader.getKeyPrefixLoadbalance() + groupId;
	}
}
