package com.bosssoft.bigdata.common.gateway.vo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * @author lucky
 * @date 2019/2/23
 * <p>
 * 扩展此类支持序列化
 * See RouteDefinition.class
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RouteDefinitionVo extends RouteDefinition implements Serializable {
	/**
	 * 路由名称
	 */
	private String routeName;
}
