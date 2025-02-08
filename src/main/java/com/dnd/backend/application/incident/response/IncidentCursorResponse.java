package com.dnd.backend.application.incident.response;

import java.util.List;

import com.dnd.backend.support.util.CursorRequest;

public record IncidentCursorResponse(
	CursorRequest nextCursorRequest,
	List<IncidentWithMediaDto> incidents
) {

}
