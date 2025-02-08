package com.dnd.backend.domain.incident.service;

public interface GeocodingService {
	String getRoadNameAddress(double pointX, double pointY);
}
