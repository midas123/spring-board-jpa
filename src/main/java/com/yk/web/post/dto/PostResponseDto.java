package com.yk.web.post.dto;

import javax.validation.constraints.NotBlank;

public class PostResponseDto {
	@NotBlank
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
