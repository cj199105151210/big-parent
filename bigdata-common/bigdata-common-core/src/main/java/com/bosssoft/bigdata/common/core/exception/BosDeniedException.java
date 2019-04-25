package com.bosssoft.bigdata.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author lucky
 * @date 😴2019-02-18 14:45
 * 403 授权拒绝
 */
@NoArgsConstructor
public class BosDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BosDeniedException(String message) {
		super(message);
	}

	public BosDeniedException(Throwable cause) {
		super(cause);
	}

	public BosDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BosDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
