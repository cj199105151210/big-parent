package com.bosssoft.bigdata.common.security.exception;

import com.bosssoft.bigdata.common.security.component.BosxAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author lucky
 * @date 2019/2/18
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = BosxAuth2ExceptionSerializer.class)
public class BosxAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public BosxAuth2Exception(String msg) {
		super(msg);
	}

	public BosxAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
