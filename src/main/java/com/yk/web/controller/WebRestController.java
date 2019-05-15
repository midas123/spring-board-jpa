package com.yk.web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.yk.web.image.UserImageService;
import com.yk.web.image.UserImagesRequestDto;
import com.yk.web.user.dto.UserRequestDto;
import com.yk.web.user.entity.Users;
import com.yk.web.user.service.UserServiceImpl;

import lombok.AllArgsConstructor;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class WebRestController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserImageService userImageService;
	
/*    @PostMapping("/registration")
    public long userRegistration(@Valid @RequestBody UserRequestDto userDto) throws IOException {
    	long userId =  userServiceImpl.userResistrationPro(userDto);
    	UserImagesRequestDto dto = userDto.toConvertUserImagesRequestDto();
    	dto.setUsers_images(new Users(userId));
    	userImageService.saveFileToDB(dto);
    	return userId;
    }*/

    
}