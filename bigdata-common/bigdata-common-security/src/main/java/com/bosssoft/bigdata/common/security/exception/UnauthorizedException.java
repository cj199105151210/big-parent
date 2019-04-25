package com.bosssoft.bigdata.common.security.exception;

import com.bosssoft.bigdata.common.security.component.BosxAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author lucky
 * @date 2019/2/18
 */
@JsonSerialize(using = BosxAuth2ExceptionSerializer.class)
public class UnauthorizedException extends BosxAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
