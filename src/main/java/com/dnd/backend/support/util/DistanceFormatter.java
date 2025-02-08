package com.dnd.backend.support.util;

public class DistanceFormatter {

	/**
	 * 1km 미만 시 -> (거리 * 1000)m (소숫점 없음)
	 * 1km 이상 시 -> km 단위, 소숫점 한 자리까지만 내림
	 */
	public static String formatDistance(double distanceInKm) {
		if (distanceInKm < 1.0) {
			long distanceInMeters = (long)Math.floor(distanceInKm * 1000);
			return distanceInMeters + "m";
		} else {
			double truncated = Math.floor(distanceInKm * 10) / 10.0;
			return truncated + "km";
		}
	}
}
