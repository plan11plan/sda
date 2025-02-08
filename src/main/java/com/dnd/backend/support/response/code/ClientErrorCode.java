package com.dnd.backend.support.response.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientErrorCode implements ErrorCode {
	VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 리소스를 찾을 수 없습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다. 로그인 후 다시 시도해주세요."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "접근이 거부되었습니다. 권한이 필요합니다.");

	private final HttpStatus httpStatus;
	private final String message;

}
