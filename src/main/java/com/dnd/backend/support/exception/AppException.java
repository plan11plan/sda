package com.dnd.backend.support.exception;

import com.dnd.backend.support.response.code.ErrorCode;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
	private final ErrorCode errorCode;

	public AppException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public AppException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}
}
