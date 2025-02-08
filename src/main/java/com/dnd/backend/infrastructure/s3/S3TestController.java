package com.dnd.backend.infrastructure.s3;

import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class S3TestController {

	private final S3FileService s3FileService;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String key = s3FileService.uploadFile(file);
		return ResponseEntity.ok("Uploaded file to S3 with key: " + key);
	}

	@GetMapping("/download/{key}")
	public ResponseEntity<String> downloadFileAsBase64(@PathVariable String key) {
		byte[] data = s3FileService.downloadFile(key);
		String base64Encoded = Base64.getEncoder().encodeToString(data);

		return ResponseEntity.ok(base64Encoded);
	}
}
