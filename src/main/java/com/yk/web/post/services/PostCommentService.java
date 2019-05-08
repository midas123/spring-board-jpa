package com.yk.web.post.services;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk.web.post.dao.PostCommentRepository;
import com.yk.web.post.dao.PostLikeRepository;
import com.yk.web.post.dto.PostCommentRequestDto;
import com.yk.web.post.dto.PostLikeRequestDto;
import com.yk.web.post.entity.PostComments;
import com.yk.web.post.entity.Posts;
import com.yk.web.user.entity.Users;

@Service
public class PostCommentService {
	@Autowired
	private PostCommentRepository postCommentRepository;
	
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	//댓글 쓰기
	public void writeComment(PostCommentRequestDto dto) {
		dto.setPost(new Posts(dto.getPost_id()));
		postCommentRepository.save(dto.toEntity());
	}
	//댓글 수정
	@Transactional
	public void updateComment(PostCommentRequestDto dto) {
		System.out.println(dto.toString());
		postCommentRepository.updateCommentContent(dto.getCom_content(), dto.getCom_id());
	}
	//댓글 추천
	@Transactional
	public void updateCommentLike(PostLikeRequestDto dto) {
		long com_id = dto.getCom_id();
		int userid = dto.getUserid();
		String nickname = dto.getNickname();
		//isLikedBefore(com_id, nickname);
		
		dto.setComment(new PostComments(com_id));
		dto.setUser(new Users(nickname, userid));
		dto.setKinds("comment");
		
		if(dto.getIsLikeUP() == true) {
			dto.setLikes(1);
			postLikeRepository.save(dto.toEntity());
			//postLikeRepository.likeUp(dto.getPost_id(), kinds);
		}	
		if(dto.getIsLikeUP() == false) {
			dto.setLikes(-1);
			postLikeRepository.save(dto.toEntity());
			//postLikeRepository.likeDown(dto.getPost_id(), kinds);
		}
	}
	
	//댓글 삭제
	public void deleteComment(PostCommentRequestDto dto) {
		postCommentRepository.deleteById(dto.getCom_id());
	}
}
