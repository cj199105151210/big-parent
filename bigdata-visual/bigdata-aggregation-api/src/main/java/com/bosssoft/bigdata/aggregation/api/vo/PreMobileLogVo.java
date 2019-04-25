package com.bosssoft.bigdata.aggregation.api.vo;

import lombok.Data;

/**
 * @author Bigdata
 * @date 2019/2/27
 * 移动前端日志vo
 */
@Data
public class PreMobileLogVo {
	private String url;
	private String time;
	private String user;
	private String type;
	private String message;
	private String stack;
	private String info;
}
