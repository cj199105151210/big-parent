package com.bosssoft.bigdata.manager.manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.bosssoft.bigdata.manager.model.ModelInfo;

/**
 * @author LCN on 2019/11/13
 */
public class ModelInfoManager {


	private static ModelInfoManager manager = null;
	private List<ModelInfo> modelInfos = new CopyOnWriteArrayList<ModelInfo>();

	public static ModelInfoManager getInstance() {
		if (manager == null) {
			synchronized (ModelInfoManager.class) {
				if (manager == null) {
					manager = new ModelInfoManager();
				}
			}
		}
		return manager;
	}

	public void removeModelInfo(String channelName) {
		for (ModelInfo modelInfo : modelInfos) {
			if (channelName.equalsIgnoreCase(modelInfo.getChannelName())) {
				modelInfos.remove(modelInfo);
			}
		}
	}


	public void addModelInfo(ModelInfo minfo) {
		for (ModelInfo modelInfo : modelInfos) {
			if (minfo.getChannelName().equalsIgnoreCase(modelInfo.getChannelName())) {
				return;
			}

			if (minfo.getIpAddress().equalsIgnoreCase(modelInfo.getIpAddress())) {
				return;
			}
		}
		modelInfos.add(minfo);
	}

	public List<ModelInfo> getOnlines() {
		return modelInfos;
	}

	public ModelInfo getModelByChannelName(String channelName) {
		for (ModelInfo modelInfo : modelInfos) {
			if (channelName.equalsIgnoreCase(modelInfo.getChannelName())) {
				return modelInfo;
			}
		}
		return null;
	}

	public ModelInfo getModelByModel(String model) {
		for (ModelInfo modelInfo : modelInfos) {
			if (model.equalsIgnoreCase(modelInfo.getModel())) {
				return modelInfo;
			}
		}
		return null;
	}
}
