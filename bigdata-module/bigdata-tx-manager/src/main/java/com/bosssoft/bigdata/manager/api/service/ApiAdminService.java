package com.bosssoft.bigdata.manager.api.service;

import java.util.List;
import java.util.Map;
import com.lorne.core.framework.exception.ServiceException;
import com.bosssoft.bigdata.manager.compensate.model.TxModel;
import com.bosssoft.bigdata.manager.model.ModelName;
import com.bosssoft.bigdata.manager.model.TxState;

/**
 * @author LCN on 2019/11/12
 */
public interface ApiAdminService {

	TxState getState();

	/**
	 * k/v 获取 值封装成map
	 *
	 * @return
	 */
	List<Map<String, Object>> getMapState();

	String loadNotifyJson();

	List<ModelName> modelList();


	List<String> modelTimes(String model);

	List<TxModel> modelInfos(String path);

	boolean compensate(String path) throws ServiceException;

	boolean hasCompensate();

	boolean delCompensate(String path);
}
