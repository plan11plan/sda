package com.dnd.backend.domain.incident.entity;

import java.util.Objects;

import com.dnd.backend.domain.incident.entity.category.DisasterGroup;
import com.dnd.backend.support.auditing.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	private double pointX;

	private double pointY;
	private String roadNameAddress;

	@Builder
	public IncidentEntity(
		Long writerId,
		String description,
		DisasterGroup disasterGroup,
		double pointX,
		double pointY,
		String roadNameAddress
	) {
		this.writerId = Objects.requireNonNull(writerId);
		this.description = Objects.requireNonNull(description);
		this.disasterGroup = Objects.requireNonNull(disasterGroup);
		this.pointX = pointX;
		this.pointY = pointY;
		this.roadNameAddress = roadNameAddress;
	}

}
