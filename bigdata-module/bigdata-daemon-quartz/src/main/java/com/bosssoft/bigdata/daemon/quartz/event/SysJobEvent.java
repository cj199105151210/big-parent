package com.bosssoft.bigdata.daemon.quartz.event;

import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.quartz.Trigger;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 * 定时任务多线程事件
 */
@Getter
@AllArgsConstructor
public class SysJobEvent {

	private final SysJob sysJob;

	private final Trigger trigger;
}
