package com.yk.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yk.web.dto.PostRequestDto;
import com.yk.web.service.PostService;

@RestController
@RequestMapping("/post")
public class PostRestController {
	private PostService postService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public String WritePost(@RequestBody PostRequestDto dto) {
		System.out.println("dto:"+dto.toString());
		postService.writePost(dto);
		return "PostDone";
	}
}
