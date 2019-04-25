package com.bosssoft.bigdata.daemon.quartz.util;

import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import com.bosssoft.bigdata.daemon.quartz.exception.TaskException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 定时任务rest反射实现
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Slf4j
@Component("restTaskInvok")
@AllArgsConstructor
public class RestTaskInvok implements ITaskInvok {

	private RestTemplate restTemplate;

	@Override
	public void invokMethod(SysJob sysJob) throws TaskException {
		R r = restTemplate.getForObject(sysJob.getExecutePath(), R.class);
		if (CommonConstants.FAIL == r.getCode()) {
			log.error("定时任务restTaskInvok异常,执行任务：{}", sysJob.getExecutePath());
			throw new TaskException("定时任务restTaskInvok业务执行失败,任务：" + sysJob.getExecutePath());
		}
	}
}
