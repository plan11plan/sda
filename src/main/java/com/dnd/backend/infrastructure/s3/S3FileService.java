package com.dnd.backend.infrastructure.s3;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dnd.backend.domain.mediaFile.service.CloudStorageService;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3FileService implements CloudStorageService {

	private final S3Template s3Template;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;

	public String uploadFile(MultipartFile file) {
		try {
			String key = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			S3Resource upload = s3Template.upload(bucket, key, file.getInputStream());
			return upload.getURL().toString();
		} catch (IOException e) {
			throw new RuntimeException("파일 업로드 실패: " + file.getOriginalFilename(), e);
		}
	}

	public byte[] downloadFile(String key) {
		try (InputStream is = s3Template.download(bucket, key).getInputStream()) {
			return is.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException("파일 다운로드 오류", e);
		}
	}
}
