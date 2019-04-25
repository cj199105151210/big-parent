package com.bosssoft.bigdata.common.core.util;

import java.io.Serializable;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author lucky
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@ApiModel(description = "返回结果")
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回码：成功=1，失败=0")
	private int code = CommonConstants.SUCCESS;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回文本信息")
	private String msg = "success";


	@Getter
	@Setter
	@ApiModelProperty(value = "业务数据")
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstants.FAIL;
	}

	public static <T> R ok(T data) {
		return  R.builder().data(data).build();
	}

	public static R ok() {
		return ok(null);
	}

	public static R fail(int code) {
		return R.builder().code(code).build();
	}

	public static R fail(int code, String msg) {
		return R.builder().code(code).msg(msg).build();
	}
}
