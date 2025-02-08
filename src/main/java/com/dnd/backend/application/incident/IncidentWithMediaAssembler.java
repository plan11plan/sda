package com.dnd.backend.application.incident;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dnd.backend.application.incident.response.IncidentWithMediaDto;
import com.dnd.backend.domain.incident.dto.IncidentDistanceDto;
import com.dnd.backend.domain.incident.entity.IncidentEntity;
import com.dnd.backend.domain.mediaFile.dto.MediaFileInfo;
import com.dnd.backend.domain.mediaFile.service.MediaFileReadService;
import com.dnd.backend.support.util.DistanceFormatter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IncidentWithMediaAssembler {

	private final MediaFileReadService mediaFileReadService;

	public List<IncidentWithMediaDto> toIncidentWithMediaDtos(List<IncidentEntity> incidents) {
		if (incidents.isEmpty()) {
			return Collections.emptyList();
		}
		var incidentIds = incidents.stream()
			.map(IncidentEntity::getId)
			.collect(Collectors.toList());

		var allMediaFiles = mediaFileReadService.getMediaFilesByIncidentIds(incidentIds);
		var mediaFilesGrouped = allMediaFiles.stream()
			.collect(Collectors.groupingBy(MediaFileInfo::incidentId));

		var incidentWriterInfo = new IncidentWriterInfo("mockNickname");

		return incidents.stream()
			.map(incident -> {
				var mediaFiles = mediaFilesGrouped.getOrDefault(incident.getId(), Collections.emptyList());
				return new IncidentWithMediaDto(incidentWriterInfo, incident, mediaFiles);
			})
			.collect(Collectors.toList());
	}

	public List<IncidentWithMediaAndDistanceDto> toIncidentWithMediaAndDistanceDtos(
		List<IncidentDistanceDto> incidentDistances) {

		if (incidentDistances.isEmpty()) {
			return Collections.emptyList();
		}

		var incidentIds = incidentDistances.stream()
			.map(dto -> dto.incident().getId())
			.toList();

		var allMediaFiles = mediaFileReadService.getMediaFilesByIncidentIds(incidentIds);
		var mediaFilesGrouped = allMediaFiles.stream()
			.collect(Collectors.groupingBy(MediaFileInfo::incidentId));

		var writer = new IncidentWriterInfo("mockNickname");

		return incidentDistances.stream()
			.map(dto -> {
				var incident = dto.incident();
				var distance = dto.distance();
				var mediaFiles = mediaFilesGrouped.getOrDefault(incident.getId(), Collections.emptyList());
				return new IncidentWithMediaAndDistanceDto(writer, incident, DistanceFormatter.formatDistance(distance),
					mediaFiles);
			})
			.collect(Collectors.toList());
	}

}
