package com.bosssoft.bigdata.daemon.quartz.event;

import com.bosssoft.bigdata.daemon.quartz.entity.SysJobLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 * 定时任务日志多线程事件
 */
@Getter
@AllArgsConstructor
public class SysJobLogEvent {
	private final SysJobLog sysJobLog;
}
