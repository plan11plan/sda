package com.dnd.backend.infrastructure.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dnd.backend.domain.incident.service.GeocodingService;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaoAddressService implements GeocodingService {

	private static final String KAKAO_COORD2ADDRESS_URL = "https://dapi.kakao.com/v2/local/geo/coord2address.json";

	@Value("${kakao.rest-api.key}")
	private String kakaoRestApiKey;

	@Override
	public String getRoadNameAddress(double pointX, double pointY) {
		AddressResponse addressResponse = convertCoordinatesToAddress(pointY, pointX);
		if (addressResponse == null) {
			return null;
		}
		// 도로명 주소가 있으면 반환, 없으면 지번 주소 반환
		if (addressResponse.getRoadAddress() != null) {
			return addressResponse.getRoadAddress();
		} else {
			return addressResponse.getBunjiAddress();
		}
	}

	public AddressResponse convertCoordinatesToAddress(Double lat, Double lng) {
		// 파라미터 검증
		if (lat == null || lng == null) {
			return null;
		}

		// Kakao Local API 호출 URL (x=경도, lng / y=위도, lat)
		String requestUrl = String.format("%s?x=%f&y=%f", KAKAO_COORD2ADDRESS_URL, lng, lat);

		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + kakaoRestApiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// 요청 엔티티 생성
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// RestTemplate 사용
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<JsonNode> response = restTemplate.exchange(
				requestUrl,
				HttpMethod.GET,
				entity,
				JsonNode.class
			);

			if (response.getStatusCode() == HttpStatus.OK) {
				JsonNode body = response.getBody();
				if (body != null
					&& body.has("documents")
					&& body.get("documents").size() > 0) {

					JsonNode firstDoc = body.get("documents").get(0);
					// 도로명 주소 정보
					JsonNode roadAddressNode = firstDoc.get("road_address");
					// 지번 주소 정보
					JsonNode addressNode = firstDoc.get("address");

					String roadAddress = null;
					String bunjiAddress = null;

					if (roadAddressNode != null && !roadAddressNode.isNull()) {
						roadAddress = roadAddressNode.get("address_name").asText();
					}
					if (addressNode != null && !addressNode.isNull()) {
						bunjiAddress = addressNode.get("address_name").asText();
					}

					return new AddressResponse(roadAddress, bunjiAddress);
				} else {
					log.info("No address information found for the given coordinates. (lat={}, lng={})", lat, lng);
					return null;
				}
			} else {
				log.warn("Request failed with status code: {}", response.getStatusCode());
				return null;
			}
		} catch (Exception e) {
			log.error("Error occurred while calling Kakao API: {}", e.getMessage(), e);
			return null;
		}
	}
}
