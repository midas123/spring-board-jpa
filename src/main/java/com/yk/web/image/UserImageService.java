package com.yk.web.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserImageService {
	private UserImagesRepository userImagesRepository;
	
	public UserImageService(UserImagesRepository userImagesRepository) {
		this.userImagesRepository = userImagesRepository;
	}
	
	public void saveFileToDB(UserImagesRequestDto dto) throws IOException {
		MultipartFile file = dto.getFile_data();
		String fileOriginName = file.getOriginalFilename();
		String fileType = file.getContentType();
		dto.setFile_type(fileType);
		dto.setFile_origin_name(fileOriginName);
		UUID uuid = UUID.randomUUID();
		dto.setFile_save_name(uuid.toString()+"_"+fileOriginName);
		dto = saveFileToPath(dto);
		userImagesRepository.save(dto.toEntity());
	}
	
	private UserImagesRequestDto saveFileToPath(UserImagesRequestDto dto) throws IOException{
		String ex_uploadPath = "C:\\Users\\yk1\\Desktop\\upload\\upload\\";
		byte[] bytes = dto.getFile_data().getBytes();
		Path path = Paths.get(ex_uploadPath+dto.getFile_origin_name());
		Files.write(path, bytes);
		dto.setFile_data(null);
		return dto;
	}

	

	public UserImages getOneUserImage(long userid) {
		return userImagesRepository.getOneUserImage(userid);
	}
}
