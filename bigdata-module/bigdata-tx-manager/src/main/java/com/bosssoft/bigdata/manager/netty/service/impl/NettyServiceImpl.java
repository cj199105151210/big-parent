package com.bosssoft.bigdata.manager.netty.service.impl;

import com.bosssoft.bigdata.manager.netty.service.IActionService;
import com.bosssoft.bigdata.manager.netty.service.NettyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2019/11/11
 */
@Service
public class NettyServiceImpl implements NettyService {

	@Autowired
	private ApplicationContext spring;

	@Override
	public IActionService getActionService(String action) {
		return spring.getBean(action, IActionService.class);
	}
}
