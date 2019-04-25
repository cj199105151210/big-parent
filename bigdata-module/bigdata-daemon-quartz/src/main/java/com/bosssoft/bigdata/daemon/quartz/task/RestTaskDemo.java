package com.bosssoft.bigdata.daemon.quartz.task;

import java.time.LocalDateTime;
import com.bosssoft.bigdata.common.core.util.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试REST风格调用的demo
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Slf4j
@RestController
@RequestMapping("/inner-job")
@Api(value = "inner-job", tags = "用于测试REST风格调用的demo")
public class RestTaskDemo {
	/**
	 * 测试REST风格调用定时任务的演示方法
	 */
	@GetMapping("/{param}")
	public R demoMethod(@PathVariable("param") String param) {
		log.info("测试于:{}，传入参数{}", LocalDateTime.now(), param);
		return R.builder().build();
	}
}
