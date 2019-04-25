package com.bosssoft.bigdata.manager.manager.service;

import com.bosssoft.bigdata.manager.netty.model.TxGroup;

/**
 *@author LCN on 2019/6/9.
 */
public interface TxManagerSenderService {

	int confirm(TxGroup group);

	String sendMsg(String model, String msg, int delay);

	String sendCompensateMsg(String model, String groupId, String data, int startState);
}
