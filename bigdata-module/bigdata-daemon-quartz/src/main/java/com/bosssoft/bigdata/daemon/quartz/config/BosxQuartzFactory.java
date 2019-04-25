package com.bosssoft.bigdata.daemon.quartz.config;

import com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum;
import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 *
 * <p>
 * 动态任务工厂
 */
@Slf4j
@DisallowConcurrentExecution
public class BosxQuartzFactory implements Job {

	@Autowired
	private BosxQuartzInvokeFactory bosxQuartzInvokeFactory;


	@Override
	@SneakyThrows
	public void execute(JobExecutionContext jobExecutionContext) {
		SysJob sysJob = (SysJob) jobExecutionContext.getMergedJobDataMap().get(BosxQuartzEnum.SCHEDULE_JOB_KEY.getType());
		bosxQuartzInvokeFactory.init(sysJob, jobExecutionContext.getTrigger());
	}
}
