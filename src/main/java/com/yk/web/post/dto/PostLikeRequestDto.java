package com.yk.web.post.dto;


import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@NoArgsConstructor
@ToString
public class PostLikeRequestDto {
	
	private String kinds;
	
	//@JsonProperty
	private boolean isLikeUP;
	
	private String nickname;
	
	private long post_id;

	@Builder
	public PostLikeRequestDto(String kinds, String nickname, long post_id) {
		this.kinds = kinds;
		this.nickname = nickname;
		this.post_id = post_id;
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

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	
}
