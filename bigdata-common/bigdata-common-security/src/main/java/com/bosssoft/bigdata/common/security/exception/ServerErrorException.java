package com.bosssoft.bigdata.common.security.exception;

import com.bosssoft.bigdata.common.security.component.BosxAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author lucky
 * @date 2019/2/18
 */
@JsonSerialize(using = BosxAuth2ExceptionSerializer.class)
public class ServerErrorException extends BosxAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
