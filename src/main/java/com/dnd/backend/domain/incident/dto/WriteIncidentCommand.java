package com.dnd.backend.domain.incident.dto;

public record WriteIncidentCommand(
	Long writerId,

	String title,

	String description,

	String disasterGroup,

	double pointX,

	double pointY
) {
}
