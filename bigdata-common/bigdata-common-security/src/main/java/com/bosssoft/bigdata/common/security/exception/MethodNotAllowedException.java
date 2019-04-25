package com.bosssoft.bigdata.common.security.exception;

import com.bosssoft.bigdata.common.security.component.BosxAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author lucky
 * @date 2019/2/18
 */
@JsonSerialize(using = BosxAuth2ExceptionSerializer.class)
public class MethodNotAllowedException extends BosxAuth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
