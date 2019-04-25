package com.bosssoft.bigdata.aggregation.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.aggregation.api.entity.MobileLog;
import com.bosssoft.bigdata.aggregation.api.vo.PreMobileLogVo;
import com.bosssoft.bigdata.aggregation.mapper.MobileLogMapper;
import com.bosssoft.bigdata.aggregation.service.MobileLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author LleytonJ
 * @since 2019-03-02
 */
@Service
public class MobileLogServiceImpl extends ServiceImpl<MobileLogMapper, MobileLog> implements MobileLogService {


	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	@Override
	public Boolean saveBatchMobileLogs(List<PreMobileLogVo> preMobileLogVoList) {
		List<MobileLog> mobileLogs = preMobileLogVoList.stream()
			.map(pre -> {
				MobileLog mobileLog = new MobileLog();
				mobileLog.setType(CommonConstants.STATUS_LOCK);
				mobileLog.setTitle(pre.getInfo());
				mobileLog.setException(pre.getStack());
				mobileLog.setParams(pre.getMessage());
				mobileLog.setCreateTime(LocalDateTime.now());
				mobileLog.setRequestUri(pre.getUrl());
				mobileLog.setCreateBy(pre.getUser());
				return mobileLog;
			})
			.collect(Collectors.toList());
		return this.saveBatch(mobileLogs);
	}
}
