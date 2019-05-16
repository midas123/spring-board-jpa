package com.yk.web.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yk.web.user.dao.UserRepository;
import com.yk.web.user.entity.Users;

@Service
public class UserImageService {
	@Autowired
    private UserRepository userRepository;
	
	private UserImagesRepository userImagesRepository;
	
	public UserImageService(UserImagesRepository userImagesRepository) {
		this.userImagesRepository = userImagesRepository;
	}
	
	public void saveFile(UserImagesRequestDto dto) throws IOException {
		dto = saveFileToPath(dto);
		userImagesRepository.save(dto.toEntity());
	}
	
	private UserImagesRequestDto saveFileToPath(UserImagesRequestDto dto) throws IOException{
		String ex_uploadPath = "C:\\Users\\yk1\\Desktop\\upload2\\images\\";
		byte[] bytes = dto.getFile_data().getBytes();
		Path path = Paths.get(ex_uploadPath+dto.getFile_save_name());
		Files.write(path, bytes);
		dto.setFile_data(null);
		return dto;
	}
	
	public UserImages serveUserImage(String username) {
		Users user = userRepository.findByUsername(username);
		UserImages userImages = userImagesRepository.FindByUserid(user.getUserid());
		return userImages;
	}

	

	public UserImages getOneUserImage(long userid) {
		return userImagesRepository.getOneUserImage(userid);
	}
}
