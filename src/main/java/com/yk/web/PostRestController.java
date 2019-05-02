package com.yk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yk.web.dto.PostRequestDto;
import com.yk.web.entity.Posts;
import com.yk.web.service.PostService;

@RestController
@RequestMapping("/post")
public class PostRestController {
	@Autowired
	private PostService postService;
	
	@PostMapping
	public String WritePost(@RequestBody PostRequestDto dto) {
		postService.writePost(dto);
		return "PostDone";
	}
	
	@GetMapping("/{post_id}")
	public Posts getPost(@PathVariable long post_id) {
		return postService.getPost(post_id);
	}
	
	
	@PutMapping("/{post_id}")
	public String updatePost(@PathVariable long post_id, @RequestBody PostRequestDto dto) {
		postService.updatePost(post_id, dto);
		return "PostUpdated";
	}
	
	@DeleteMapping("/{post_id}")
	public String deletePost(@PathVariable long post_id) {
		postService.deletePost(post_id);
		return "PostDeleted";
	}
	
	@GetMapping("/list")
	public List<Posts> PostList(){
		return postService.ListingPost();
	}
}
