package com.bosssoft.bigdata.daemon.quartz.config;

import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import com.bosssoft.bigdata.daemon.quartz.event.SysJobEvent;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.quartz.Trigger;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Aspect
@Slf4j
@AllArgsConstructor
public class BosxQuartzInvokeFactory {

	private final ApplicationEventPublisher publisher;

	@SneakyThrows
	void init(SysJob sysJob, Trigger trigger) {
		publisher.publishEvent(new SysJobEvent(sysJob, trigger));
	}
}
