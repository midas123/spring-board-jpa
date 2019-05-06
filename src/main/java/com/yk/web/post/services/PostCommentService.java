package com.yk.web.post.services;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk.web.post.dao.PostCommentRepository;
import com.yk.web.post.dao.PostLikeRepository;
import com.yk.web.post.dto.PostCommentRequestDto;
import com.yk.web.post.dto.PostLikeRequestDto;
import com.yk.web.post.entity.PostComments;

@Service
public class PostCommentService {
	@Autowired
	private PostCommentRepository postCommentRepository;
	
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	//댓글 쓰기
	public void writeComment(PostComments dto) {
		postCommentRepository.save(dto);
	}
	//댓글 수정
	public void updateComment(PostCommentRequestDto dto) {
		postCommentRepository.updateCommentContent(dto.getCom_content(), dto.getCom_id());
	}
	//댓글 삭제
	public void deleteComment(PostCommentRequestDto dto) {
		postCommentRepository.delete(dto.toEntity());
	}
	//댓글 추천
	@Transactional
	public void updateCommentLike(PostLikeRequestDto dto) {
	//isLikedBefore(dto.getPost_id(), dto.getNickname());
		if(dto.getIsLikeUP() == true) {
			postLikeRepository.likeUpComment(dto.getPost_id());
		}	
		if(dto.getIsLikeUP() == false) {
			postLikeRepository.likeDownComment(dto.getPost_id());
		}
		
	}
}
