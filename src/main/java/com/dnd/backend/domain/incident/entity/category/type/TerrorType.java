package com.dnd.backend.domain.incident.entity.category.type;

import com.dnd.backend.domain.incident.entity.category.DisasterGroup;
import com.dnd.backend.domain.incident.entity.category.DisasterType;

public enum TerrorType implements DisasterType {
	RIOT("폭동"),
	TERROR("테러");

	private final String name;

	TerrorType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DisasterGroup getParentType() {
		return DisasterGroup.테러;
	}
}
