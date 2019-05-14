package com.yk.web.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserImageService {
	private UserImagesRepository userImagesRepository;
	
	public UserImageService(UserImagesRepository userImagesRepository) {
		this.userImagesRepository = userImagesRepository;
	}
	
	public void saveFile(UserImagesRequestDto dto) {
		userImagesRepository.save(dto.toEntity());
	}
}
