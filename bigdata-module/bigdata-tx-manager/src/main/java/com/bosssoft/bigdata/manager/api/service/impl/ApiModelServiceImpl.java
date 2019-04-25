package com.bosssoft.bigdata.manager.api.service.impl;

import java.util.List;
import com.bosssoft.bigdata.manager.api.service.ApiModelService;
import com.bosssoft.bigdata.manager.manager.ModelInfoManager;
import com.bosssoft.bigdata.manager.model.ModelInfo;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2019/11/13
 */
@Service
public class ApiModelServiceImpl implements ApiModelService {


	@Override
	public List<ModelInfo> onlines() {
		return ModelInfoManager.getInstance().getOnlines();
	}


}
