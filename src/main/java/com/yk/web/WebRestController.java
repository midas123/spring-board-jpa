package com.yk.web;

import org.springframework.web.bind.annotation.RestController;

import com.yk.web.dao.UserRequestDto;
import com.yk.web.service.UserServiceImpl;

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
	
	
    @PostMapping("/join")
    public int joinMember(@Valid @RequestBody UserRequestDto UserDto) {
    	return userServiceImpl.registerUser(UserDto);
    }
    
 /*   
   	//스프링 시큐리티 적용으로 주석처리
    @PostMapping("/login")
    public String loginMember(@RequestBody MemberRequestDto MemberDto) {
    	return membersService.userLogin(MemberDto).getNickname();
    }*/
    
}