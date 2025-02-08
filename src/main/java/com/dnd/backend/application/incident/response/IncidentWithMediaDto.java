package com.dnd.backend.application.incident.response;

import java.util.List;

import com.dnd.backend.application.incident.IncidentWriterInfo;
import com.dnd.backend.domain.incident.entity.IncidentEntity;
import com.dnd.backend.domain.mediaFile.dto.MediaFileInfo;

public record IncidentWithMediaDto(
	IncidentWriterInfo writer,
	IncidentEntity incident,
	List<MediaFileInfo> mediaFiles
) {
}
