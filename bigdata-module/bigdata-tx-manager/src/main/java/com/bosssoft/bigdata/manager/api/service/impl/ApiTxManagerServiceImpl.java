package com.bosssoft.bigdata.manager.api.service.impl;


import com.bosssoft.bigdata.manager.api.service.ApiTxManagerService;
import com.bosssoft.bigdata.manager.compensate.model.TransactionCompensateMsg;
import com.bosssoft.bigdata.manager.compensate.service.CompensateService;
import com.bosssoft.bigdata.manager.config.ConfigReader;
import com.bosssoft.bigdata.manager.manager.service.MicroService;
import com.bosssoft.bigdata.manager.manager.service.TxManagerSenderService;
import com.bosssoft.bigdata.manager.manager.service.TxManagerService;
import com.bosssoft.bigdata.manager.model.TxServer;
import com.bosssoft.bigdata.manager.model.TxState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2019/7/1.
 */
@Service
@AllArgsConstructor
public class ApiTxManagerServiceImpl implements ApiTxManagerService {

	private final TxManagerService managerService;

	private final MicroService eurekaService;

	private final CompensateService compensateService;

	private final TxManagerSenderService txManagerSenderService;

	private final ConfigReader configReader;


	@Override
	public TxServer getServer() {
		return eurekaService.getServer();
	}


	@Override
	public int cleanNotifyTransaction(String groupId, String taskId) {
		return managerService.cleanNotifyTransaction(groupId, taskId);
	}


	@Override
	public boolean sendCompensateMsg(long currentTime, String groupId, String model, String address, String uniqueKey, String className, String methodStr, String data, int time, int startError) {
		TransactionCompensateMsg transactionCompensateMsg = new TransactionCompensateMsg(currentTime, groupId, model, address, uniqueKey, className, methodStr, data, time, 0, startError);
		return compensateService.saveCompensateMsg(transactionCompensateMsg);
	}

	@Override
	public String sendMsg(String model, String msg) {
		return txManagerSenderService.sendMsg(model, msg, configReader.getTransactionNettyDelayTime());
	}


	@Override
	public TxState getState() {
		return eurekaService.getState();
	}
}
