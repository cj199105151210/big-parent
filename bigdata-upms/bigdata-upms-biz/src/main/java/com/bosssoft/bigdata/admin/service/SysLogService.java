package com.bosssoft.bigdata.admin.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.bigdata.admin.api.entity.SysLog;
import com.bosssoft.bigdata.admin.api.vo.PreLogVo;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author lucky
 * @since 2017-11-20
 */
public interface SysLogService extends IService<SysLog> {


	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	Boolean saveBatchLogs(List<PreLogVo> preLogVoList);
}
