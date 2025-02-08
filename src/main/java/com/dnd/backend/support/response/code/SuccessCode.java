package com.dnd.backend.support.response.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements ResponseCode {
	OK(HttpStatus.OK, "요청이 성공적으로 처리되었습니다.");

	private final HttpStatus httpStatus;
	private final String message;

}
