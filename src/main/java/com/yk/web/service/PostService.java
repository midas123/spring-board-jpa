package com.yk.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yk.web.dao.PostRepository;
import com.yk.web.dto.PostRequestDto;
import com.yk.web.entity.Posts;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	//게시글 쓰기
	public void writePost(PostRequestDto dto) {
		postRepository.save(dto.toEntity());
	}
	//게시글 목록
	public List<Posts> ListingPost(){
		return postRepository.findAll();
	}
	
	//게시글 조회
	@Transactional
	public Posts getPost(long post_id) {
		updatePostHits(post_id);
		return postRepository.findById(post_id);
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

	//게시글 추천 테이블 따로
	
	
	
}
