package com.bosssoft.bigdata.daemon.quartz.config;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Configuration
public class BosxQuartzCustomizerConfig implements SchedulerFactoryBeanCustomizer {
	@Override
	public void customize(SchedulerFactoryBean schedulerFactoryBean) {
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
	}
}
