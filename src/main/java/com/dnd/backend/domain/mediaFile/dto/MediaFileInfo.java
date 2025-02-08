package com.dnd.backend.domain.mediaFile.dto;

public record MediaFileInfo(
	Long incidentId,
	String mediaType,
	String fileUrl) {
}
