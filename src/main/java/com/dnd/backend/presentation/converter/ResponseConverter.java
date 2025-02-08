package com.dnd.backend.presentation.converter;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.dnd.backend.support.exception.ResponseConversionException;
import com.dnd.backend.support.response.Response;
import com.dnd.backend.support.response.code.SuccessCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * 성공 응답 데이터를 Response.SuccessCode 객체로 변환하는 로직
 */
@Component
@RequiredArgsConstructor
public class ResponseConverter {
	private final ObjectMapper objectMapper;

	public Object convert(Object body,
		Class<? extends HttpMessageConverter<?>> converterType,
		ServerHttpResponse response) {
		Response successResponse = new Response.Success(SuccessCode.OK, body);

		if (isJacksonConverter(converterType)) {
			return successResponse;
		}

		return convertToJsonString(successResponse, response);
	}

	private boolean isJacksonConverter(Class<? extends HttpMessageConverter<?>> converterType) {
		return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
	}

	/**
	 * Response 객체를 JSON 문자열로 변환합니다.
	 */
	private String convertToJsonString(Response response, ServerHttpResponse httpResponse) {
		try {
			httpResponse.getHeaders().set("Content-Type", "application/json");
			return objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			throw new ResponseConversionException(e);
		}
	}
}
