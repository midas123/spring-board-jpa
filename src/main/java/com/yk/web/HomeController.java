package com.yk.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@AllArgsConstructor
public class HomeController {
	
	private MembersRepository memberRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @PostMapping("/join")
    public void joinMember(@RequestBody MemberJoinRequestDto dto) {
    	memberRepository.save(dto.toEntity());
    }

}