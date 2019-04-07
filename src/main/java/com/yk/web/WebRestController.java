package com.yk.web;

import org.springframework.web.bind.annotation.RestController;

import com.yk.web.dao.UserRequestDto;
import com.yk.web.service.UserServiceImpl;

import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private UserServiceImpl userService;
	
	
    @PostMapping("/join")
    public int joinMember(@Valid @RequestBody UserRequestDto UserDto) {
    	return userService.registerUser(UserDto);
    }
    
 /*   
   	//스프링 시큐리티 적용으로 주석처리
    @PostMapping("/login")
    public String loginMember(@RequestBody MemberRequestDto MemberDto) {
    	return membersService.userLogin(MemberDto).getNickname();
    }*/
    
}