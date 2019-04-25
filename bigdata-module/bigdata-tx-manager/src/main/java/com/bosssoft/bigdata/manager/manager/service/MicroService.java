package com.bosssoft.bigdata.manager.manager.service;

import com.bosssoft.bigdata.manager.model.TxServer;
import com.bosssoft.bigdata.manager.model.TxState;

/**
 * @author LCN on 2019/11/11
 */
public interface MicroService {

	String TMKEY = "tx-manager";

	TxServer getServer();

	TxState getState();
}
