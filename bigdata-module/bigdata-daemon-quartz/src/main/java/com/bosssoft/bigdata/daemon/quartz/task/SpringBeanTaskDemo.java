package com.bosssoft.bigdata.daemon.quartz.task;

import java.time.LocalDateTime;
import com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Slf4j
@Component("demo")
public class SpringBeanTaskDemo {
	/**
	 * 测试Spring Bean的演示方法
	 */
	@SneakyThrows
	public String demoMethod(String para) {
		log.info("测试于:{}，输入参数{}", LocalDateTime.now(), para);
		return BosxQuartzEnum.JOB_LOG_STATUS_SUCCESS.getType();
	}
}
