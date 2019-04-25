package com.bosssoft.bigdata.daemon.quartz.event;


import com.bosssoft.bigdata.daemon.quartz.entity.SysJobLog;
import com.bosssoft.bigdata.daemon.quartz.service.SysJobLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 * 异步监听定时任务日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysJobLogListener {

	private SysJobLogService sysJobLogService;

	@Async
	@Order
	@EventListener(SysJobLogEvent.class)
	public void saveSysJobLog(SysJobLogEvent event) {
		SysJobLog sysJobLog = (SysJobLog) event.getSysJobLog();
		sysJobLogService.save(sysJobLog);
		log.info("执行定时任务日志");
	}
}
