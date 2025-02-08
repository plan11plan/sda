package com.dnd.backend.application.incident;

import org.springframework.stereotype.Component;

import com.dnd.backend.application.incident.response.IncidentCursorResponse;
import com.dnd.backend.domain.incident.service.IncidentReadService;
import com.dnd.backend.support.util.CursorRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetIncidentsByCursorUseCase {

	private final IncidentReadService incidentReadService;
	private final IncidentWithMediaAssembler incidentWithMediaAssembler;

	public IncidentCursorResponse execute(Long writerId, CursorRequest cursorRequest) {

		var incidentCursor = incidentReadService.getIncidents(writerId, cursorRequest);
		var incidents = incidentCursor.contents();

		var withMediaList = incidentWithMediaAssembler.toIncidentWithMediaDtos(incidents);

		return new IncidentCursorResponse(incidentCursor.nextCursorRequest(), withMediaList);
	}
}
