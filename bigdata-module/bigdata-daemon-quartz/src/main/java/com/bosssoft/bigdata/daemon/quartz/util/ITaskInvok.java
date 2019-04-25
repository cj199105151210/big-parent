package com.bosssoft.bigdata.daemon.quartz.util;

import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import com.bosssoft.bigdata.daemon.quartz.exception.TaskException;

/**
 * 定时任务反射实现接口类
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
public interface ITaskInvok {

	/**
	 * 执行反射方法
	 *
	 * @param sysJob 配置类
	 * @throws TaskException
	 */
	void invokMethod(SysJob sysJob) throws TaskException;
}
