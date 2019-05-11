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
import com.yk.web.post.entity.PostComments;
import com.yk.web.post.entity.PostLikes;
import com.yk.web.post.entity.Posts;
import com.yk.web.post.valid.PostLikeException;
import com.yk.web.user.entity.Users;

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
		List<Posts> pl = postRepository.getAllPost();
		for(int i=0; i<pl.size(); i++) {
			Posts post = pl.get(i);
			List<PostLikes> postlikelist = post.getPostLikes();
			if(postlikelist.size()>0) {
				postlikelist = Posts.addAllLikes(postlikelist, "post");
				post.setPostLikes(postlikelist);
			}
			if(post.getComments() != null) {
				List<PostComments> com = post.getComments();
				for(int j=0; j<com.size(); j++) {
					if(com.get(j) != null) {
						List<PostLikes> ps = Posts.addAllLikes(post.getComments().get(j).getPostLikes(), "comment");
						com.get(j).setPostLikes(ps);
					}
				}
			}
		}
		return pl;
	}
	
	//게시글 조회
	@Transactional
	public Posts getPost(long post_id) {
		updatePostHits(post_id);
		Posts post = postRepository.getPost(post_id);
		if(post.getPostLikes() != null) {
			List<PostLikes> ps = Posts.addAllLikes(post.getPostLikes(), "post");
			post.setPostLikes(ps);
		}
		if(post.getComments() != null) {
			List<PostComments> com = post.getComments();
			for(int i=0; i<com.size(); i++) {
				if(com.get(i) != null) {
					List<PostLikes> ps = Posts.addAllLikes(post.getComments().get(i).getPostLikes(), "comment");
					com.get(i).setPostLikes(ps);
				}
			}
		}
		return post;
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
	
	//게시글 추천
	@Transactional
	public void likePost(PostLikeRequestDto dto) {
		long postid = dto.getPost_id();
		isLikedBefore(postid, dto.getNickname());
		dto.setPost(new Posts(postid));
		dto.setKinds("post");
		
		if(dto.getIsLikeUP() == true) {
			dto.setLikes(1);
			postLikeRepository.save(dto.toEntity());
		}	
		if(dto.getIsLikeUP() == false) {
			dto.setLikes(-1);
			postLikeRepository.save(dto.toEntity());
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
