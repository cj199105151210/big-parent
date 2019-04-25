package com.bosssoft.bigdata.manager.compensate.dao;

import java.util.List;
import com.bosssoft.bigdata.manager.compensate.model.TransactionCompensateMsg;

/**
 * @author LCN on 2019/11/11
 */
public interface CompensateDao {

	String saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

	List<String> loadCompensateKeys();

	List<String> loadCompensateTimes(String model);

	List<String> loadCompensateByModelAndTime(String path);

	String getCompensate(String key);

	String getCompensateByGroupId(String groupId);

	void deleteCompensateByPath(String path);

	void deleteCompensateByKey(String key);

	boolean hasCompensate();
}
