package com.dnd.backend.support.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class HealthCheckController {
	@GetMapping
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("Health Check OK");
	}
}
