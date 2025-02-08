package com.dnd.backend.domain.mediaFile.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dnd.backend.domain.mediaFile.entity.MediaFileEntity;
import com.dnd.backend.domain.mediaFile.entity.MediaType;
import com.dnd.backend.domain.mediaFile.repository.JpaMediaFileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaFileWriteService {
	private final CloudStorageService cloudStorageService;
	private final JpaMediaFileRepository mediaFileRepository;

	public void uploadFiles(Long incidentId, List<MultipartFile> files) {

		int currentFileCount = mediaFileRepository.countByIncidentId(incidentId);
		validateMaximumUpload(files, currentFileCount);

		for (MultipartFile file : files) {
			String fileUrl = cloudStorageService.uploadFile(file);
			MediaType mediaType = determineFileType(file);

			MediaFileEntity fileEntity = MediaFileEntity.builder()
				.incidentId(incidentId)
				.fileType(mediaType)
				.fileUrl(fileUrl)
				.originalFilename(file.getOriginalFilename())
				.build();
			mediaFileRepository.save(fileEntity);
		}
	}

	private void validateMaximumUpload(List<MultipartFile> files, int currentFileCount) {
		if (currentFileCount + files.size() > 3) {
			throw new IllegalArgumentException("파일은 최대 3개까지 업로드 가능합니다.");
		}
	}

	private MediaType determineFileType(MultipartFile file) {
		String contentType = file.getContentType();
		//TODO: 영상 확장자 로직 개선 필요
		if (contentType != null && contentType.startsWith("video")) {
			return MediaType.VIDEO;
		}
		return MediaType.IMAGE;
	}
}
