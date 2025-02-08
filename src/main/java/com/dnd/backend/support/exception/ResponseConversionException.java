package com.dnd.backend.support.exception;

import com.dnd.backend.support.response.code.ServerErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ResponseConversionException extends AppException {
	public ResponseConversionException(JsonProcessingException error) {
		super(ServerErrorCode.JSON_CONVERSION_ERROR, error);
	}
}
