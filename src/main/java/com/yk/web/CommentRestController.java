package com.yk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yk.web.post.dto.PostCommentRequestDto;
import com.yk.web.post.dto.PostLikeRequestDto;
import com.yk.web.post.services.PostCommentService;

@RestController
public class CommentRestController {
	@Autowired
	PostCommentService postCommentService;
	
	@PostMapping("/comment")
	public String writeComment(@RequestBody PostCommentRequestDto dto) {
		System.out.println(dto.toString());
		postCommentService.writeComment(dto.toEntity());
		return "CommentDone";
	}
	
	@PutMapping("/comment")
	public String modifyComment(PostCommentRequestDto dto) {
		postCommentService.updateComment(dto);
		return "modifyDone";
	}
	
	@PutMapping("/comment/like")
	public String likesComment(PostLikeRequestDto dto) {
		postCommentService.updateCommentLike(dto);
		return "commentLiked";
	}
	
	@DeleteMapping("/comment")
	public String deleteComment(PostCommentRequestDto dto) {
		postCommentService.deleteComment(dto);
		return "commentDeleted";
	}
	
}
