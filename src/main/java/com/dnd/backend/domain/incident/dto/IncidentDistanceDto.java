package com.dnd.backend.domain.incident.dto;

import com.dnd.backend.domain.incident.entity.IncidentEntity;

public record IncidentDistanceDto(
	IncidentEntity incident,
	double distance
) {
}

