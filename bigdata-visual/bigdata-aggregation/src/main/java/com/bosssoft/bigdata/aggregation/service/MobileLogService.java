package com.bosssoft.bigdata.aggregation.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.bigdata.aggregation.api.entity.MobileLog;
import com.bosssoft.bigdata.aggregation.api.vo.PreMobileLogVo;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author LleytonJ
 * @since 2019-03-02
 */
public interface MobileLogService extends IService<MobileLog> {

	/**
	 * 批量插入前端错误日志
	 *
	 * @param preMobileLogVoList 日志信息
	 * @return true/false
	 */
	Boolean saveBatchMobileLogs(List<PreMobileLogVo> preMobileLogVoList);
}
