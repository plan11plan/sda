package com.dnd.backend.support.response.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServerErrorCode implements ErrorCode {
	INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
	JSON_CONVERSION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JSON 변환 실패");

	private final HttpStatus httpStatus;
	private final String message;

}
