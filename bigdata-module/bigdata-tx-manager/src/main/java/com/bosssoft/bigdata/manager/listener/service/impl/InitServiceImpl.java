package com.bosssoft.bigdata.manager.listener.service.impl;

import com.bosssoft.bigdata.manager.config.ConfigReader;
import com.bosssoft.bigdata.manager.framework.utils.Constants;
import com.bosssoft.bigdata.manager.listener.service.InitService;
import com.bosssoft.bigdata.manager.netty.service.NettyServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author LCN on 2019/7/4.
 */
@Service
public class InitServiceImpl implements InitService {

	@Autowired
	private NettyServerService nettyServerService;

	@Autowired
	private ConfigReader configReader;


	@Override
	public void start() {
		/**加载本地服务信息**/

		Constants.socketPort = configReader.getSocketPort();
		Constants.maxConnection = configReader.getSocketMaxConnection();
		nettyServerService.start();
	}

	@Override
	public void close() {
		nettyServerService.close();
	}
}
