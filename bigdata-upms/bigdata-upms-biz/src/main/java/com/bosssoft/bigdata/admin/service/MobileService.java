package com.bosssoft.bigdata.admin.service;

import com.bosssoft.bigdata.common.core.util.R;

/**
 * @author lucky
 * @date 2018/11/14
 */
public interface MobileService {
	/**
	 * 发送手机验证码
	 *
	 * @param mobile mobile
	 * @return code
	 */
	R<Boolean> sendSmsCode(String mobile);
}
