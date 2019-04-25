package com.bosssoft.bigdata.daemon.quartz.exception;

/**
 * 定时任务异常
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
public class TaskException extends Exception {

	public TaskException() {
		super();
	}

	public TaskException(String msg) {
		super(msg);
	}
}
