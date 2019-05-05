package com.yk.web.post.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yk.web.post.dao.PostLikeRepository;
import com.yk.web.post.dao.PostRepository;
import com.yk.web.post.dto.PostLikeRequestDto;
import com.yk.web.post.dto.PostRequestDto;
import com.yk.web.post.dto.PostResponseDto;
import com.yk.web.post.entity.PostLikes;
import com.yk.web.post.entity.Posts;
import com.yk.web.post.valid.PostLikeException;
import com.yk.web.user.valid.ValidCustomException;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	//게시글 쓰기
	public void writePost(PostRequestDto dto) {
		postRepository.save(dto.toEntity());
	}
	
	//모든 게시글 목록 조회
	public List<Posts> getAllPost(){
		return postRepository.getAllPost();
	}
	
	//게시글 조회
	@Transactional
	public Posts getPost(long post_id) {
		updatePostHits(post_id);
		return postRepository.getPostWithLike(post_id);
	}
	
	//게시글 조회수+
	private void updatePostHits(long post_id) {
		postRepository.updatePostCounts(post_id);
	}
	
	//게시글 수정
	@Transactional
	public void updatePost(long post_id, PostRequestDto dto) {
		postRepository.updatePostTitleAndContent(post_id ,dto.getP_title() ,dto.getP_content());
	}
	
	//게시글 삭제
	public void deletePost(long post_id) {
		postRepository.deleteById(post_id);
	}
	
	//게시글 비공개(관리자)
	@Transactional
	public void blindPost(long post_id) {
		postRepository.updatePostBlinded(post_id);
	}

	//게시글 추천
	@Transactional
	public void likePost(PostLikeRequestDto dto) {
		isLikedBefore(dto.getPost_id(), dto.getNickname());
		
		if(dto.getIsLikeUP() == true) {
			postLikeRepository.likeUp(dto.getPost_id());
		}	
		if(dto.getIsLikeUP() == false) {
			postLikeRepository.likeDown(dto.getPost_id());
		}
	}
	
	//중복 추천 방지
	private void isLikedBefore(long post_id, String nickname) {
		Optional<PostLikes> PostLikesOp = postLikeRepository.isLikedCheck(post_id, nickname);
		if(PostLikesOp.isPresent()) {
			throw new PostLikeException("이미 추천한 글 입니다.");
		}
	}
	
	
	
}
