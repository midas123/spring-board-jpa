package com.yk.web;

import org.springframework.web.bind.annotation.RestController;

import com.yk.web.member.MemberJoinRequestDto;
import com.yk.web.member.MembersService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private MembersService membersService;

    @PostMapping("/join")
    public void joinMember(@RequestBody MemberJoinRequestDto dto) {
    	System.out.println("join");
    	System.out.println(dto);
    	membersService.join(dto);
    }

}