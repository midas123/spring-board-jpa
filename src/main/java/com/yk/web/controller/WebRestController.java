package com.yk.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yk.web.image.UserImageService;
import com.yk.web.image.UserImagesRequestDto;
import com.yk.web.user.dto.UserRequestDto;
import com.yk.web.user.entity.Users;
import com.yk.web.user.service.UserServiceImpl;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@AllArgsConstructor
public class WebRestController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserImageService userImageService;
	
    @PostMapping("/registration")
    public long userRegistration(@Valid UserRequestDto userDto, 
    		@RequestParam("file_data") MultipartFile uploadfile) throws IOException {
    	long userId =  userServiceImpl.userResistrationPro(userDto);
    	UserImagesRequestDto dto = userDto.toConvertUserImagesRequestDto();
    	dto.setUsers_images(new Users(userId));
    	
    	MultipartFile file = dto.getFile_data();
		String fileOriginName = file.getOriginalFilename();
		String fileType = file.getContentType();
		dto.setFile_type(fileType);
		dto.setFile_origin_name(fileOriginName);
    	UUID uuid = UUID.randomUUID();
    	dto.setFile_save_name(uuid.toString()+"_"+fileOriginName);
    	
    	userImageService.saveFile(dto);
    	
    	return userId;
    }
    
    @PostMapping("/registration/2")
    public long userRegistration(@Valid @RequestBody UserRequestDto UserDto) {
    	return userServiceImpl.userResistrationPro(UserDto);
    }
    
}