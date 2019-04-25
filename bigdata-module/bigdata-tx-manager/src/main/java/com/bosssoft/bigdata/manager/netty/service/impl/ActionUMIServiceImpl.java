package com.bosssoft.bigdata.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bosssoft.bigdata.manager.framework.utils.SocketManager;
import com.bosssoft.bigdata.manager.manager.ModelInfoManager;
import com.bosssoft.bigdata.manager.model.ModelInfo;
import com.bosssoft.bigdata.manager.netty.service.IActionService;
import org.springframework.stereotype.Service;

/**
 * 上传模块信息
 * @author LCN on 2019/11/11
 */
@Service(value = "umi")
public class ActionUMIServiceImpl implements IActionService {


	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res = "1";

		String uniqueKey = params.getString("u");
		String ipAddress = params.getString("i");
		String model = params.getString("m");


		ModelInfo modelInfo = new ModelInfo();
		modelInfo.setChannelName(channelAddress);
		modelInfo.setIpAddress(ipAddress);
		modelInfo.setModel(model);
		modelInfo.setUniqueKey(uniqueKey);

		ModelInfoManager.getInstance().addModelInfo(modelInfo);

		SocketManager.getInstance().onLine(channelAddress, uniqueKey);

		return res;
	}
}
