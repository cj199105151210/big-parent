package com.bosssoft.bigdata.manager.api.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.hutool.core.util.StrUtil;
import com.bosssoft.bigdata.manager.api.service.ApiAdminService;
import com.bosssoft.bigdata.manager.compensate.model.TxModel;
import com.bosssoft.bigdata.manager.compensate.service.CompensateService;
import com.bosssoft.bigdata.manager.manager.service.MicroService;
import com.bosssoft.bigdata.manager.model.ModelName;
import com.bosssoft.bigdata.manager.model.TxState;
import com.bosssoft.bigdata.manager.redis.RedisServerService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2019/11/12
 */
@Service
@AllArgsConstructor
public class ApiAdminServiceImpl implements ApiAdminService {
	private final MicroService eurekaService;
	private final RedisServerService redisServerService;
	private final CompensateService compensateService;

	@Override
	public TxState getState() {
		return eurekaService.getState();
	}

	/**
	 * k/v 获取 值封装成map
	 *
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getMapState() {
		TxState txState = getState();
		Map<String, Object> result = new HashMap<>(16);
		result.put("Socket对外服务IP", txState.getIp());
		result.put("Socket对外服务端口", txState.getPort());
		result.put("最大连接数", txState.getMaxConnection());
		result.put("当前连接数", txState.getNowConnection());
		result.put("TxManager模块心跳间隔时间(秒)", txState.getTransactionNettyHeartTime());
		result.put("TxManager模块通讯最大等待时间(秒)", txState.getCompensateMaxWaitTime());
		result.put("redis服务状态", StrUtil.isBlank(loadNotifyJson()) ? "异常" : "正常");
		result.put("redis存储最大时间(秒)", txState.getRedisSaveMaxTime());
		result.put("负载均衡服务器地址", txState.getSlbList());
		result.put("存在补偿数据", hasCompensate() ? "存在" : "不存在");
		result.put("补偿回调地址", txState.getNotifyUrl());
		result.put("自动补偿", txState.isCompensate());
		result.put("补偿尝试时间", txState.getCompensateTryTime());
		result.put("自动补偿间隔时间", txState.getCompensateMaxWaitTime());

		List<Map<String, Object>> resultList = new ArrayList<>();
		result.forEach((k, v) -> {
			Map<String, Object> one = new HashMap<>(2);
			one.put("key", k);
			one.put("value", v);
			resultList.add(one);
		});
		return resultList;
	}

	@Override
	public String loadNotifyJson() {
		return redisServerService.loadNotifyJson();
	}

	@Override
	public List<ModelName> modelList() {
		return compensateService.loadModelList();
	}


	@Override
	public List<String> modelTimes(String model) {
		return compensateService.loadCompensateTimes(model);
	}

	@Override
	public List<TxModel> modelInfos(String path) {
		return compensateService.loadCompensateByModelAndTime(path);
	}

	@Override
	@SneakyThrows
	public boolean compensate(String path) {
		return compensateService.executeCompensate(path);
	}

	@Override
	public boolean delCompensate(String path) {
		return compensateService.delCompensate(path);
	}

	@Override
	public boolean hasCompensate() {
		return compensateService.hasCompensate();
	}
}
