package com.bosssoft.bigdata.common.core.constant;

/**
 * @author lucky
 * @date 2019/2/18
 */
public interface CommonConstants {
	/**
	 * header 中租户ID
	 */
	String TENANT_ID = "TENANT_ID";
	/**
	 * 删除
	 */
	String STATUS_DEL = "1";
	/**
	 * 正常
	 */
	String STATUS_NORMAL = "0";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * 前端工程名 TODO 代码生成打包文件夹
	 */
	String FRONT_END_PROJECT = "bigdata-ui";

	/**
	 * 后端工程名 TODO 代码生成打包文件夹
	 */
	String BACK_END_PROJECT = "bigdata";

	/**
	 * 路由存放
	 */
	String ROUTE_KEY = "gateway_route_key";

	/**
	 * spring boot admin 事件key
	 */
	String EVENT_KEY = "event_key";

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 1;
	/**
	 * 失败标记
	 */
	Integer FAIL = 0;
	/**
	 * TOKEN失败标记
	 */
    Integer TOKEN_FAIL = 7;

	/**
	 * 默认存储bucket
	 */
	String BUCKET_NAME = "bigdata";

	/**
	 * 用户登录错误锁定次数限制
	 */
	Integer ERROR_COUNT=5;

	String BOSX_PASSWORD_PREFIX = "bosx:password:error:account:";

	/**
	 * redis过期时间
	 */
	Integer expire = 30;

	/**
	 * 树形数据根节点id
	 */
	Integer TREE_ROOT_ID = -1;

	/**
	 * 系统用户初始密码
	 */
	String INITIAL_PASSWORD = "123456";


	/**
	 * 绑定类型
	 */
	public static class BindType {
		//手机绑定第三方账号
		public static final Integer PHONE_TO_UUID = 1;
		//第三方账号绑定手机
		public static final Integer UUID_TO_PHONE = 2;
	}

	/**
	 * 用户类型
	 */
	public static class PublicUserType{
		//个人用户
		public static final Integer PERSONAL_USER = 0;
		//企业用户
		public static final Integer ENTERPRISE_USER = 1;
	}

	/**
	 * 认证登记
	 */
	public static class AuthLevel{
		public static final String NONE = "0"; //未认证
		public static final String CERT_JUNIOR = "1"; //普通认证
		public static final String CERT_SENIOR = "2"; //高级认证
		public static final String REAL_NAME_AUTHENTICATION = "9"; //实名认证
	}

}
