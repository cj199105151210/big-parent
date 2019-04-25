package com.bosssoft.bigdata.aggregation.api.feign;

import com.bosssoft.bigdata.common.core.constant.SecurityConstants;
import com.bosssoft.bigdata.common.core.constant.ServiceNameConstants;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.aggregation.api.entity.MobileLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * @Author: Bigdata
 * @Description: 移动端日志表
 * @Date: Created in 14:37 2019/3/2
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstants.MOBILE_SERVICE)
public interface RemoteMobileLogService {

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/mobile/{id}")
	public String demo(@PathVariable("id") String id);

	/**
	 * 保存移动端日志
	 *
	 * @param sysLog 日志实体
	 * @param from   是否内部调用
	 * @return succes、false
	 */
	@PostMapping("/mobile/save")
    R<Boolean> saveLog(@RequestBody MobileLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);


}
