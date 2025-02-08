package com.dnd.backend.presentation.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.dnd.backend.presentation.converter.ResponseConverter;
import com.dnd.backend.presentation.converter.ResponseTypeChecker;

import lombok.RequiredArgsConstructor;

/**
 * 정상 응답을 Response.SuccessCode 형태로 래핑해서 반환하는 Advice
 */
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.dnd.backend")
public class ApiSuccessHandler implements ResponseBodyAdvice<Object> {

	private final ResponseTypeChecker responseTypeChecker;
	private final ResponseConverter responseConverter;

	/**
	 * 어떤 응답을 후처리할지 결정
	 * - Response 타입이면 이미 래핑된 상태이므로 처리하지 않음
	 */
	@Override
	public boolean supports(MethodParameter returnType,
		Class<? extends HttpMessageConverter<?>> converterType) {
		return responseTypeChecker.shouldHandle(returnType);
	}

	/**
	 * 정상 응답을 공통 포맷(Response.SuccessCode)으로 변환
	 */
	@Override
	public Object beforeBodyWrite(Object body,
		MethodParameter returnType,
		MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType,
		ServerHttpRequest request,
		ServerHttpResponse response) {

		return responseConverter.convert(body, selectedConverterType, response);
	}
}
