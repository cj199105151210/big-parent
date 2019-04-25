package com.bosssoft.bigdata.common.core.exception;

/**
 * 验证代码异常
 * @author lucky
 * @date 😴2019-02-18 14:45
 */
public class ValidateCodeException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValidateCodeException() {
	}

	public ValidateCodeException(String msg) {
		super(msg);
	}
}
