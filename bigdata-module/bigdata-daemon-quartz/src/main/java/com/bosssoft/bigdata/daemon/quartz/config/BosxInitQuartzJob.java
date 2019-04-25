package com.bosssoft.bigdata.daemon.quartz.config;


import com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum;
import com.bosssoft.bigdata.daemon.quartz.service.SysJobService;
import com.bosssoft.bigdata.daemon.quartz.util.TaskUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Lleyton J.
 * @date 2019-03-31
 * <p>
 * 初始化加载定时任务
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class BosxInitQuartzJob {
	private final SysJobService sysJobService;
	private final TaskUtil taskUtil;
	private final Scheduler scheduler;

	@Bean
	public void customize() {
		sysJobService.list().forEach(sysjob -> {
			if (BosxQuartzEnum.JOB_STATUS_RELEASE.getType().equals(sysjob.getJobStatus())) {
				taskUtil.removeJob(sysjob, scheduler);
			} else if (BosxQuartzEnum.JOB_STATUS_RUNNING.getType().equals(sysjob.getJobStatus())) {
				taskUtil.resumeJob(sysjob, scheduler);
			} else if (BosxQuartzEnum.JOB_STATUS_NOT_RUNNING.getType().equals(sysjob.getJobStatus())) {
				taskUtil.pauseJob(sysjob, scheduler);
			} else {
				taskUtil.removeJob(sysjob, scheduler);
			}
		});
	}
}
