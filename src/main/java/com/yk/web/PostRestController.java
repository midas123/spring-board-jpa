package com.yk.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yk.web.post.dto.PostLikeRequestDto;
import com.yk.web.post.dto.PostRequestDto;
import com.yk.web.post.entity.Posts;
import com.yk.web.post.services.PostService;

@RestController
public class PostRestController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/post/{post_id}")
	public Posts getPost(@PathVariable long post_id) {
		return postService.getPost(post_id);
	}
	
	@GetMapping("/post/all")
	public List<Posts> PostList(){
		return postService.getAllPost();
	}

	@PostMapping("/post")
	public String WritePost(@RequestBody PostRequestDto dto) {
		postService.writePost(dto);
		return "PostDone";
	}
	
	@PutMapping("/post/{post_id}")
	public String updatePost(@PathVariable long post_id, @RequestBody PostRequestDto dto) {
		postService.updatePost(post_id, dto);
		return "PostUpdated";
	}
	
	@DeleteMapping("/post/{post_id}")
	public String deletePost(@PathVariable long post_id) {
		postService.deletePost(post_id);
		return "PostDeleted";
	}
	
	/*@PutMapping("/post/admin/{post_id}")
	public String blindPost(@PathVariable long post_id) {
		postService.blindPost(post_id);
		return "PostBlinded";
	}*/
	
	@PutMapping("/post/like")
	public String likedPost(@Valid @RequestBody PostLikeRequestDto dto) {
		System.out.println("controller:"+dto.toString());
		postService.likePost(dto);
		return "PostLiked";
	}
}
