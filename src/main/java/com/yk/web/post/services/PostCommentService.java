package com.yk.web.post.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk.web.post.dao.PostCommentRepository;
import com.yk.web.post.dao.PostLikeRepository;
import com.yk.web.post.dto.PostCommentRequestDto;
import com.yk.web.post.dto.PostLikeRequestDto;
import com.yk.web.post.entity.PostComments;
import com.yk.web.post.entity.PostLikes;
import com.yk.web.post.entity.Posts;
import com.yk.web.post.valid.PostLikeException;
import com.yk.web.user.entity.Users;

@Service
public class PostCommentService {
	@Autowired
	private PostCommentRepository postCommentRepository;
	
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	//댓글 쓰기
	public void writeComment(PostCommentRequestDto dto) {
		//게시글 댓글(그룹) 순서
		long post_id = dto.getPost_id();
		dto.setPost(new Posts(post_id));
		Long com_group_seq = postCommentRepository.getComGroupMaxValue(post_id);
		
		if(com_group_seq != null) {
			dto.setCom_group_seq(com_group_seq+1);
		} else {
			dto.setCom_group_seq(0);
		}
		postCommentRepository.save(dto.toEntity());
	}
	//댓글 답변(대댓글)
	public void replyComment(PostCommentRequestDto dto) {
		long com_group_seq = dto.getCom_group_seq();
		long post_id = dto.getPost_id();
		dto.setCom_depth(1);
		dto.setPost(new Posts(post_id));
		
		Long com_re_seq = postCommentRepository.getComReMaxValue(com_group_seq, post_id);
		if(com_re_seq != null) {
			dto.setCom_re_seq(com_re_seq+1);
		}
		postCommentRepository.save(dto.toEntity());
	}

	//댓글 수정
	@Transactional
	public void updateComment(PostCommentRequestDto dto) {
		postCommentRepository.modfiyCommentContent(dto.getCom_content(), dto.getCom_id());
	}
	
	//댓글 추천
	@Transactional
	public void updateCommentLike(PostLikeRequestDto dto) {
		long com_id = dto.getCom_id();
		String nickname = dto.getNickname();
		isLikedComBefore(com_id, nickname);
		
		dto.setComment(new PostComments(com_id));
		dto.setKinds("comment");
		
		
		if(dto.getIsLikeUP() == true) {
			dto.setLikes(1);
			postLikeRepository.save(dto.toEntity());
		}	
		if(dto.getIsLikeUP() == false) {
			dto.setLikes(-1);
			postLikeRepository.save(dto.toEntity());
		}
	}
	
	private void isLikedComBefore(long com_id, String nickname) {
		Optional<PostLikes> PostLikesOp = postLikeRepository.isLikedComCheck(com_id, nickname);
		if(PostLikesOp.isPresent()) {
			throw new PostLikeException("이미 추천한 댓글 입니다.");
		}
	}
	
	
	//댓글 삭제
	@Transactional
	public void deleteComment(PostCommentRequestDto dto) {
		String com_content = "삭제된 댓글입니다.";
		postCommentRepository.deleteMessageInComment(com_content, true, dto.getCom_id());
	}
}
