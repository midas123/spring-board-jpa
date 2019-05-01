package com.yk.web;

import org.springframework.web.bind.annotation.RestController;

import com.yk.web.dto.PostRequestDto;
import com.yk.web.dto.UserRequestDto;
import com.yk.web.entity.Posts;
import com.yk.web.service.PostService;
import com.yk.web.service.UserServiceImpl;

import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	private PostService boardsService;
	
	
    @PostMapping("/registration")
    public int userRegistration(@Valid @RequestBody UserRequestDto UserDto) {
    	return userServiceImpl.userResistrationPro(UserDto);
    }

    
}