package com.dnd.backend.infrastructure.kakao;

public class AddressResponse {
	private String roadAddress;   // 도로명 주소
	private String bunjiAddress;  // 지번 주소

	// 기본 생성자
	public AddressResponse() {
	}

	public AddressResponse(String roadAddress, String bunjiAddress) {
		this.roadAddress = roadAddress;
		this.bunjiAddress = bunjiAddress;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getBunjiAddress() {
		return bunjiAddress;
	}

	public void setBunjiAddress(String bunjiAddress) {
		this.bunjiAddress = bunjiAddress;
	}

	@Override
	public String toString() {
		return "AddressResponse{" +
			"roadAddress='" + roadAddress + '\'' +
			", bunjiAddress='" + bunjiAddress + '\'' +
			'}';
	}
}
