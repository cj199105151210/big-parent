package com.bosssoft.bigdata.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bosssoft.bigdata.manager.manager.service.LoadBalanceService;
import com.bosssoft.bigdata.manager.model.LoadBalanceInfo;
import com.bosssoft.bigdata.manager.netty.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取负载模块信息
 * @author LCN on 2019/11/11
 */
@Service(value = "glb")
public class ActionGLBServiceImpl implements IActionService {


	@Autowired
	private LoadBalanceService loadBalanceService;


	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res;
		String groupId = params.getString("g");
		String k = params.getString("k");

		LoadBalanceInfo loadBalanceInfo = loadBalanceService.get(groupId, k);
		if (loadBalanceInfo == null) {
			res = "";
		} else {
			res = loadBalanceInfo.getData();
		}
		return res;
	}
}
