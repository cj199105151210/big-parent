package com.bosssoft.bigdata.daemon.quartz.event;

import com.bosssoft.bigdata.daemon.quartz.config.BosxQuartzInvokeFactory;
import com.bosssoft.bigdata.daemon.quartz.service.SysJobLogService;
import com.bosssoft.bigdata.daemon.quartz.util.TaskInvokUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 * <p>
 * 多线程自动配置
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class EventAutoConfiguration {
	@Autowired
	private TaskInvokUtil taskInvokUtil;
	@Autowired
	private SysJobLogService sysJobLogService;

	@Bean
	public SysJobListener sysJobListener() {
		return new SysJobListener(taskInvokUtil);
	}

	@Bean
	public BosxQuartzInvokeFactory bosxQuartzInvokeFactory(ApplicationEventPublisher publisher) {
		return new BosxQuartzInvokeFactory(publisher);
	}

	@Bean
	public SysJobLogListener sysJobLogListener() {
		return new SysJobLogListener(sysJobLogService);
	}

	@Bean
	public TaskInvokUtil taskInvokUtil(ApplicationEventPublisher publisher) {
		return new TaskInvokUtil(publisher);
	}

}
