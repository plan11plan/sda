package com.dnd.backend.domain.incident.entity.category.type;

import com.dnd.backend.domain.incident.entity.category.DisasterGroup;
import com.dnd.backend.domain.incident.entity.category.DisasterType;

public enum TrafficType implements DisasterType {
	CONTROL("교통통제"),
	ACCIDENT("교통사고");

	private final String name;

	TrafficType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DisasterGroup getParentType() {
		return DisasterGroup.교통;
	}
}
