package com.yk.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yk.web.PostResourceAssembler;
import com.yk.web.post.dto.PostLikeRequestDto;
import com.yk.web.post.dto.PostRequestDto;
import com.yk.web.post.entity.Posts;
import com.yk.web.post.services.PostService;

@RestController
public class PostRestController {
	@Autowired
	private PostService postService;
	
	@Autowired
	PostResourceAssembler assembler;
	
	@GetMapping("/post/all")
	public Resources<Resource<Posts>> postList(){
		List<Resource<Posts>> posts = postService.getAllPost().stream()
				.map(assembler::toResource).collect(Collectors.toList());
		
		return new Resources<>(posts,
				linkTo(methodOn(PostRestController.class).postList()).withSelfRel());
	}
	
	@GetMapping("/post/{post_id}")
	public Resource<Posts> getOnePost(@PathVariable long post_id) {
		Posts post = postService.getOnePost(post_id);
		Resource<Posts> resource = new Resource<Posts>(post);
		Link link = new Link("http://localhost:8080/post/all");
		resource.add(link);
		return resource;
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
	
	@PutMapping("/post/like")
	public String likedPost(@Valid @RequestBody PostLikeRequestDto dto) {
		System.out.println("controller:"+dto.toString());
		postService.likePost(dto);
		return "PostLiked";
	}
}
