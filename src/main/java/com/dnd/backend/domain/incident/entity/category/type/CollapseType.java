package com.dnd.backend.domain.incident.entity.category.type;

import com.dnd.backend.domain.incident.entity.category.DisasterGroup;
import com.dnd.backend.domain.incident.entity.category.DisasterType;

public enum CollapseType implements DisasterType {
	EMERGENCY("비상");

	private final String name;

	CollapseType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DisasterGroup getParentType() {
		return DisasterGroup.붕괴;
	}
}
