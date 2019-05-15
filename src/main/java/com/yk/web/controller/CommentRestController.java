package com.yk.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		postCommentService.writeComment(dto);
		return "CommentDone";
	}
	
	@PutMapping("/comment")
	public String modifyComment(@RequestBody PostCommentRequestDto dto) {
		postCommentService.updateComment(dto);
		return "modifyDone";
	}
	
	@PostMapping("/comment/reply")
	public String replyComment(@RequestBody PostCommentRequestDto dto) {
		postCommentService.replyComment(dto);
		return "commentReplied";
	}
	
	@PutMapping("/comment/like")
	public String likesComment(@RequestBody PostLikeRequestDto dto) {
		postCommentService.updateCommentLike(dto);
		return "commentLiked";
	}
	
	@DeleteMapping("/comment")
	public String deleteComment(@RequestBody PostCommentRequestDto dto) {
		postCommentService.deleteComment(dto);
		return "commentDeleted";
	}
	
}
