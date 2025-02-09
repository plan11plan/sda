package com.dnd.backend.presentation.controller.incident;

import com.dnd.backend.application.incident.CreateIncidentUseCase;
import com.dnd.backend.application.incident.GetIncidentsByCursorUseCase;
import com.dnd.backend.application.incident.GetNearIncidentsUseCase;
import com.dnd.backend.application.incident.IncidentWithMediaAndDistanceDto;
import com.dnd.backend.application.incident.response.IncidentCursorResponse;
import com.dnd.backend.domain.incident.dto.WriteIncidentCommand;
import com.dnd.backend.domain.incident.entity.IncidentEntity;
import com.dnd.backend.domain.incident.service.IncidentReadService;
import com.dnd.backend.support.util.CursorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/incidents")
public class IncidentController {

    private final CreateIncidentUseCase createIncidentUseCase;
    private final GetIncidentsByCursorUseCase getIncidentsByCursorUseCase;
    private final GetNearIncidentsUseCase getNearIncidentsUsecase;
    private final IncidentReadService incidentReadService;

    @GetMapping("/test/findAll")
    public List<IncidentEntity> createIncident() {
        return incidentReadService.findAll();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public void createIncident(
            @RequestPart("incidentData") WriteIncidentCommand command,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        //TODO : Null처리 DTO 캡슐화하기
        if (files == null) {
            files = Collections.emptyList();
        }
        createIncidentUseCase.execute(command, files);
    }

    @GetMapping("/writer/{writerId}")
    public IncidentCursorResponse getWriterIncidentsByCursor(
            @PathVariable("writerId") Long writerId,
            @ModelAttribute CursorRequest cursorRequest) {

        return getIncidentsByCursorUseCase.execute(writerId, cursorRequest);
    }

    @GetMapping("/nearby")
    public List<IncidentWithMediaAndDistanceDto> getIncidentsWithinDistance(
            @RequestParam double pointX,
            @RequestParam double pointY,
            @RequestParam(defaultValue = "5") double radiusInKm
    ) {
        return getNearIncidentsUsecase.execute(pointX, pointY, radiusInKm);
    }
}
