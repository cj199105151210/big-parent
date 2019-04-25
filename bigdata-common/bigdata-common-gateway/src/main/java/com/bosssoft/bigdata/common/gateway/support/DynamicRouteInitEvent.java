package com.bosssoft.bigdata.common.gateway.support;

import org.springframework.context.ApplicationEvent;

/**
 * @author lucky
 * @date 2019/2/23
 * <p>
 * 路由初始化事件
 */
public class DynamicRouteInitEvent extends ApplicationEvent {
	public DynamicRouteInitEvent(Object source) {
		super(source);
	}
}
