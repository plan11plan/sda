package com.dnd.backend.support.exception;

import com.dnd.backend.support.response.code.ClientErrorCode;

public class IncidentNotFoundException extends AppException {
	public IncidentNotFoundException() {
		super(ClientErrorCode.NOT_FOUND);
	}
}
