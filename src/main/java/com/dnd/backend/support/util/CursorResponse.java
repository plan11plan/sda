package com.dnd.backend.support.util;

import java.util.List;

public record CursorResponse<T>(
	CursorRequest nextCursorRequest,
	List<T> contents

) {

}
