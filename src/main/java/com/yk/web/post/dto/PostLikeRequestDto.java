package com.yk.web.post.dto;


import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yk.web.post.entity.PostComments;
import com.yk.web.post.entity.PostLikes;
import com.yk.web.post.entity.Posts;
import com.yk.web.user.entity.Users;

import lombok.Builder;

@NoArgsConstructor
@ToString
public class PostLikeRequestDto {
	
	private String kinds;
	
	private long likes;
	//@JsonProperty
	private boolean isLikeUP;
	
	private String nickname;
	
	private long post_id;
	
	private int userid;
	
	private long com_id;
	
	@JsonIgnore
	private Posts post;
	
	@JsonIgnore
	private PostComments comment;
	
	
	public PostLikes toEntity() {
		return PostLikes.builder()
			.likes(likes)
			.comment(comment)
			.post(post)
			.nickname(nickname)
			.kinds(kinds)
			.build();
	}
	
	public long getPost_id() {
		return post_id;
	}
	
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	
	public Posts getPost() {
		return post;
	}
	
	public void setPost(Posts post) {
		this.post = post;
	}

	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	public boolean getIsLikeUP() {
		return isLikeUP;
	}

	public void setIsLikeUP(boolean isLikeUP) {
		this.isLikeUP = isLikeUP;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}


	public void setLikeUP(boolean isLikeUP) {
		this.isLikeUP = isLikeUP;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public long getCom_id() {
		return com_id;
	}


	public void setCom_id(long com_id) {
		this.com_id = com_id;
	}


	public PostComments getComment() {
		return comment;
	}


	public void setComment(PostComments comment) {
		this.comment = comment;
	}
	
}
