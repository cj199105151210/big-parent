package com.bosssoft.bigdata.daemon.quartz.event;

import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import com.bosssoft.bigdata.daemon.quartz.util.TaskInvokUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;


/**
 * @author Lleyton J.
 * @date 2019-03-31
 * 异步监听定时任务事件
 */
@Slf4j
@AllArgsConstructor
public class SysJobListener {

	private TaskInvokUtil taskInvokUtil;

	@Async
	@Order
	@EventListener(SysJobEvent.class)
	public void comSysJob(SysJobEvent event) {
		SysJob sysJob = event.getSysJob();
		Trigger trigger = event.getTrigger();
		taskInvokUtil.invokMethod(sysJob, trigger);
	}
}
