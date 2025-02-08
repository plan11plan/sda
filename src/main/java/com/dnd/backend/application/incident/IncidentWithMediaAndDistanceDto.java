package com.dnd.backend.application.incident;

import java.util.List;

import com.dnd.backend.domain.incident.entity.IncidentEntity;
import com.dnd.backend.domain.mediaFile.dto.MediaFileInfo;

public record IncidentWithMediaAndDistanceDto(
	IncidentWriterInfo writer,
	IncidentEntity incident,
	String distance,
	List<MediaFileInfo> mediaFiles) {
}
