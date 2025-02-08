package com.dnd.backend.application.incident;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dnd.backend.domain.incident.dto.WriteIncidentCommand;
import com.dnd.backend.domain.incident.service.IncidentWriteService;
import com.dnd.backend.domain.mediaFile.service.MediaFileWriteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateIncidentUseCase {
	private final IncidentWriteService incidentWriteService;
	private final MediaFileWriteService mediaFileWriteService;

	@Transactional
	public void execute(WriteIncidentCommand command, List<MultipartFile> files) {
		var incidentId = incidentWriteService.create(command);
		mediaFileWriteService.uploadFiles(incidentId, files);
	}
}
