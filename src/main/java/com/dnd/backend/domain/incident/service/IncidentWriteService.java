package com.dnd.backend.domain.incident.service;

import static com.dnd.backend.domain.incident.entity.category.DisasterGroup.*;

import org.springframework.stereotype.Service;

import com.dnd.backend.domain.incident.dto.WriteIncidentCommand;
import com.dnd.backend.domain.incident.entity.IncidentEntity;
import com.dnd.backend.domain.incident.repository.JpaIncidentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncidentWriteService {
	private final JpaIncidentRepository incidentCommandRepository;
	private final GeocodingService geocodingService;

	public Long create(WriteIncidentCommand command) {
		var disasterGroup = mapToDisasterGroup(command.disasterGroup());

		var roadNameAddress = geocodingService.getRoadNameAddress(command.pointX(), command.pointY());

		var incidentEntity = IncidentEntity.builder()
			.writerId(command.writerId())
			.description(command.description())
			.disasterGroup(disasterGroup)
			.pointX(command.pointX())
			.pointY(command.pointY())
			.roadNameAddress(roadNameAddress)
			.build();

		return incidentCommandRepository.save(incidentEntity).getId();
	}
}
