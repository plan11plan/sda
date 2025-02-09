package com.dnd.backend.domain.incident.service;

import com.dnd.backend.domain.incident.dto.IncidentDistanceDto;
import com.dnd.backend.domain.incident.entity.IncidentEntity;
import com.dnd.backend.domain.incident.repository.JpaIncidentRepository;
import com.dnd.backend.support.util.CursorRequest;
import com.dnd.backend.support.util.CursorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@RequiredArgsConstructor
public class IncidentReadService {
    private final JpaIncidentRepository incidentQueryRepository;

    public List<IncidentEntity> findAll() {
        return incidentQueryRepository.findAll();
    }

    public CursorResponse<IncidentEntity> getIncidents(Long writerId, CursorRequest cursorRequest) {
        var incidents = findAllBy(writerId, cursorRequest);
        var nextKey = incidents.stream()
                .mapToLong(IncidentEntity::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);

        return new CursorResponse<>(cursorRequest.next(nextKey), incidents);
    }

    private List<IncidentEntity> findAllBy(Long writerId, CursorRequest cursorRequest) {
        var pageable = PageRequest.of(
                0,
                cursorRequest.size(),
                Sort.by(DESC, "id")
        );
        if (cursorRequest.hasKey()) {
            return incidentQueryRepository.findAllByWriterIdAndIdLessThan(
                    writerId,
                    cursorRequest.key(),
                    pageable);
        }
        return incidentQueryRepository.findAllByWriterId(
                writerId,
                pageable);
    }

    public List<IncidentDistanceDto> findNearbyIncidents(double pointX, double pointY, double radiusKm) {
        List<IncidentEntity> allIncidents = incidentQueryRepository.findAll();

        return allIncidents.stream()
                .map(incident -> {
                    double distance = calculateDistance(pointX, pointY,
                            incident.getPointY(), incident.getPointX());
                    return new IncidentDistanceDto(incident, distance);
                })
                .filter(dto -> dto.distance() <= radiusKm)
                .collect(Collectors.toList());
    }

    // Haversine 공식을 사용하여 두 좌표 간의 거리 계산 (단위: km)
    private double calculateDistance(double x1, double y1, double x2, double y2) {
        final int R = 6371; // 지구의 반지름 (km)
        double latDistance = Math.toRadians(x2 - x1);
        double lonDistance = Math.toRadians(y2 - y1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // 거리 (km)
    }
}

