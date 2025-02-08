package com.dnd.backend.support.util;

public record CursorRequest(Long key, int size) {
	public static final Long NONE_KEY = -1L;

	public CursorRequest next(Long key) {
		return new CursorRequest(key, size);
	}

	public Boolean hasKey() {
		return key != null;
	}
}
