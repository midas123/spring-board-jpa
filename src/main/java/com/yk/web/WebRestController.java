package com.yk.web;

import org.springframework.web.bind.annotation.RestController;

import com.yk.web.user.dto.UserRequestDto;
import com.yk.web.user.service.UserServiceImpl;

import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class WebRestController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
    @PostMapping("/registration")
    public int userRegistration(@Valid @RequestBody UserRequestDto UserDto) {
    	return userServiceImpl.userResistrationPro(UserDto);
    }

    
}