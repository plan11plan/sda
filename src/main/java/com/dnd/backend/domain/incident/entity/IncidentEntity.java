package com.dnd.backend.domain.incident.entity;

import com.dnd.backend.domain.incident.entity.category.DisasterGroup;
import com.dnd.backend.support.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Table(name = "incidents")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IncidentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long writerId;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private DisasterGroup disasterGroup;

    private double pointY;

    private double pointX;
    private String roadNameAddress;

    @Builder
    public IncidentEntity(
            Long writerId,
            String description,
            DisasterGroup disasterGroup,
            double pointY,
            double pointX,
            String roadNameAddress
    ) {
        this.writerId = Objects.requireNonNull(writerId);
        this.description = Objects.requireNonNull(description);
        this.disasterGroup = Objects.requireNonNull(disasterGroup);
        this.pointY = pointY;
        this.pointX = pointX;
        this.roadNameAddress = roadNameAddress;
    }

}
