package com.dnd.backend.domain.incident.entity.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.dnd.backend.domain.incident.entity.category.type.CollapseType;
import com.dnd.backend.domain.incident.entity.category.type.ExplosionType;
import com.dnd.backend.domain.incident.entity.category.type.FineDustType;
import com.dnd.backend.domain.incident.entity.category.type.FireType;
import com.dnd.backend.domain.incident.entity.category.type.NaturalDisasterType;
import com.dnd.backend.domain.incident.entity.category.type.TerrorType;
import com.dnd.backend.domain.incident.entity.category.type.TrafficType;

public enum DisasterGroup {
	교통("교통", List.of(TrafficType.values())),
	화재("화재", List.of(FireType.values())),
	붕괴("붕괴", List.of(CollapseType.values())),
	폭발("폭발", List.of(ExplosionType.values())),
	자연재난("자연재난", List.of(NaturalDisasterType.values())),
	미세먼지("미세먼지", List.of(FineDustType.values())),
	테러("테러", List.of(TerrorType.values())),
	EMPTY("없음", Collections.EMPTY_LIST);

	private final String displayName;
	private final Map<String, DisasterType> subTypeMap;

	DisasterGroup(String name, List<DisasterType> subTypes) {
		this.displayName = name;
		this.subTypeMap = subTypes.stream()
			.collect(Collectors.toMap(DisasterType::getName, Function.identity()));
	}

	public static DisasterGroup mapToDisasterGroup(String groupName) {
		return Arrays.stream(DisasterGroup.values())
			.filter(group -> group.getName().equals(groupName))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 재난 그룹: " + groupName));
	}

	public Optional<DisasterType> getDisasterSubType(String name) {
		return Optional.ofNullable(subTypeMap.get(name));
	}

	public boolean hasDisasterSubType(DisasterType disasterType) {
		return subTypeMap.containsValue(disasterType);
	}

	public String getName() {
		return displayName;
	}

	public List<DisasterType> getSubTypes() {
		return new ArrayList<>(subTypeMap.values());
	}
}
