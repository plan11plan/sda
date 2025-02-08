package com.dnd.backend.support.response.code;

public sealed interface ErrorCode extends ResponseCode
	permits ClientErrorCode, ServerErrorCode {
	String getMessage();
}
