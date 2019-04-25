package com.bosssoft.bigdata.admin.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.admin.api.entity.SysLog;
import com.bosssoft.bigdata.admin.api.vo.PreLogVo;
import com.bosssoft.bigdata.admin.mapper.SysLogMapper;
import com.bosssoft.bigdata.admin.service.SysLogService;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author lucky
 * @since 2017-11-20
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	@Override
	public Boolean saveBatchLogs(List<PreLogVo> preLogVoList) {
		List<SysLog> sysLogs = preLogVoList.stream()
			.map(pre -> {
				SysLog log = new SysLog();
				log.setType(CommonConstants.STATUS_LOCK);
				log.setTitle(pre.getInfo());
				log.setException(pre.getStack());
				log.setParams(pre.getMessage());
				log.setCreateTime(LocalDateTime.now());
				log.setRequestUri(pre.getUrl());
				log.setCreateBy(pre.getUser());
				return log;
			})
			.collect(Collectors.toList());
		return this.saveBatch(sysLogs);
	}
}
