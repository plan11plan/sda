package com.dnd.backend.presentation.converter;

import java.lang.reflect.ParameterizedType;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.dnd.backend.support.response.Response;

/**
 * 응답 타입이 이미 Response인지, ResponseEntity인지 등을 체크하여
 * 이미 래핑된 경우 다시 래핑하지 않도록 결정
 */
@Component
public class ResponseTypeChecker {

	public boolean shouldHandle(MethodParameter returnType) {
		Class<?> type = getActualType(returnType);
		return !isResponseType(type); // Response 타입이면 래핑 X
	}

	private boolean isResponseType(Class<?> type) {
		return Response.class.isAssignableFrom(type);
	}

	private boolean isResponseEntity(Class<?> type) {
		return ResponseEntity.class.isAssignableFrom(type);
	}

	private Class<?> getActualType(MethodParameter returnType) {
		Class<?> type = returnType.getParameterType();
		if (isResponseEntity(type)) {
			try {
				var parameterizedType = (ParameterizedType)returnType.getGenericParameterType();
				return (Class<?>)parameterizedType.getActualTypeArguments()[0];
			} catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
				return Object.class;
			}
		}
		return type;
	}
}
