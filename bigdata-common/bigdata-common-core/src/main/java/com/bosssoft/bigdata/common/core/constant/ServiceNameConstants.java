package com.bosssoft.bigdata.common.core.constant;

/**
 * @author lucky
 * @date 2019/2/18
 * 服务名称
 */
public interface ServiceNameConstants {
	/**
	 * 认证中心
	 */
	String AUTH_SERVICE = "bigdata-auth";

	/**
	 * 通用用户权限模块
	 */
	String UMPS_SERVICE = "bigdata-upms-biz";

	/**
	 * 分布式事务协调服务
	 */
	String TX_MANAGER = "bigdata-tx-manager";

	/**
	 * 移动平台服务
	 */
	String MOBILE_SERVICE = "bigdata-mobile-manager";

    /**
     * 支付平台服务
     */
    String PAY_SERVICE = "bigdata-pay-manager";

    /**
     * 消息平台服务
     */
    String MSGCNT_SERVICE = "bigdata-msgcnt-manager";

	/**
	 * 聚合平台服务
	 */
	String AGGREGAT_SERVICE = "bigdata-aggregation";

	/**
	 * 用户中心服务
	 */
	String USERCENTER_SERVICE = "bigdata-usercenter";
}
