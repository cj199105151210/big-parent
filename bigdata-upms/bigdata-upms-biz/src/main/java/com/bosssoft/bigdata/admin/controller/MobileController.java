package com.bosssoft.bigdata.admin.controller;

import com.bosssoft.bigdata.admin.service.MobileService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucky
 * @date 2019-04-24
 * <p>
 * 手机验证码
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "手机管理模块")
public class MobileController {
	private final MobileService mobileService;

	//不允许接口/方法开放访问,需要用户登录验证才可以访问
	//@Inner(value = true)
	//允许接口/方法开放访问
	@Inner(value = false)
	@GetMapping("/{mobile}")
	public R sendSmsCode(@PathVariable String mobile) {
		return mobileService.sendSmsCode(mobile);
	}
}
