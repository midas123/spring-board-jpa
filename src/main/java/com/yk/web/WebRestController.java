package com.yk.web;

import org.springframework.web.bind.annotation.RestController;

import com.yk.web.member.MemberRequestDto;
import com.yk.web.member.MembersService;
import lombok.AllArgsConstructor;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private MembersService membersService;
	
    @PostMapping("/join")
    public Long joinMember(@Valid @RequestBody MemberRequestDto MemberDto) {
    	return membersService.join(MemberDto);
    }
    
    @PostMapping("/login")
    public String loginMember(@Valid @RequestBody MemberRequestDto MemberDto) {
    	return membersService.login(MemberDto).getNickname();
    }
    
}