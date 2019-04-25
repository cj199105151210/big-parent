package com.bosssoft.bigdata.manager.compensate.service;

import java.util.List;
import com.lorne.core.framework.exception.ServiceException;
import com.bosssoft.bigdata.manager.compensate.model.TransactionCompensateMsg;
import com.bosssoft.bigdata.manager.compensate.model.TxModel;
import com.bosssoft.bigdata.manager.model.ModelName;
import com.bosssoft.bigdata.manager.netty.model.TxGroup;

/**
 * @author LCN on 2019/11/11
 */
public interface CompensateService {

	boolean saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

	List<ModelName> loadModelList();

	List<String> loadCompensateTimes(String model);

	List<TxModel> loadCompensateByModelAndTime(String path);

	void autoCompensate(String compensateKey, TransactionCompensateMsg transactionCompensateMsg);

	boolean executeCompensate(String key) throws ServiceException;

	void reloadCompensate(TxGroup txGroup);

	boolean hasCompensate();

	boolean delCompensate(String path);

	TxGroup getCompensateByGroupId(String groupId);
}
