package com.bosssoft.bigdata.manager.api.controller;

import com.bosssoft.bigdata.manager.api.service.ApiTxManagerService;
import com.bosssoft.bigdata.manager.model.TxServer;
import com.bosssoft.bigdata.manager.model.TxState;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author LCN on 2019/7/1.
 *
 * @author LCN
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tx/manager")
public class TxManagerController {
	private final ApiTxManagerService apiTxManagerService;

	@RequestMapping("/getServer")
	public TxServer getServer() {
		return apiTxManagerService.getServer();
	}


	@RequestMapping("/cleanNotifyTransaction")
	public int cleanNotifyTransaction(@RequestParam("groupId") String groupId, @RequestParam("taskId") String taskId) {
		return apiTxManagerService.cleanNotifyTransaction(groupId, taskId);
	}


	@RequestMapping("/sendMsg")
	public String sendMsg(@RequestParam("msg") String msg, @RequestParam("model") String model) {
		return apiTxManagerService.sendMsg(model, msg);
	}


	@RequestMapping("/sendCompensateMsg")
	public boolean sendCompensateMsg(@RequestParam("model") String model, @RequestParam("uniqueKey") String uniqueKey,
                                     @RequestParam("currentTime") long currentTime,
                                     @RequestParam("groupId") String groupId, @RequestParam("className") String className,
                                     @RequestParam("time") int time, @RequestParam("data") String data,
                                     @RequestParam("methodStr") String methodStr, @RequestParam("address") String address,
                                     @RequestParam("startError") int startError) {
		return apiTxManagerService.sendCompensateMsg(currentTime, groupId, model, address, uniqueKey, className, methodStr, data, time, startError);
	}


	@RequestMapping("/state")
	public TxState state() {
		return apiTxManagerService.getState();
	}
}
