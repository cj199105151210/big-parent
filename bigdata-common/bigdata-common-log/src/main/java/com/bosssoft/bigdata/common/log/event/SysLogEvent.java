package com.bosssoft.bigdata.common.log.event;

import com.bosssoft.bigdata.admin.api.entity.SysLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lucky
 * 系统日志事件
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {
	private final SysLog sysLog;
}
