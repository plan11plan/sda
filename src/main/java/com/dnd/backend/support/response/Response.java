package com.dnd.backend.support.response;

import static com.dnd.backend.support.response.Response.*;

import java.time.LocalDateTime;

import com.dnd.backend.support.response.code.ErrorCode;
import com.dnd.backend.support.response.code.ResponseCode;

public sealed interface Response<T> permits Success, Fail {

	static <T> Success<T> success(ResponseCode code, T data) {
		return new Success<>(code, data);
	}

	static <T> Fail<T> fail(ErrorCode code, String message, T data) {
		return new Fail<>(code, message, data);
	}

	record Success<T>(ResponseCode code, T data, LocalDateTime timestamp) implements Response<T> {
		public Success(ResponseCode code, T data) {
			this(code, data, LocalDateTime.now());
		}
	}

	record Fail<T>(ErrorCode code, String message, T data, LocalDateTime timestamp) implements Response<T> {
		public Fail(ErrorCode code, String message, T data) {
			this(code, message, data, LocalDateTime.now());
		}
	}
}
