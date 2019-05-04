package com.yk.web.post.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.yk.web.post.entity.PostLikes;

import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostLikeRequestDto {
	private String kinds;
	
	private boolean isUP;
	
	private String nickname;
	
	private long post_id;
	

	@Builder
	public PostLikeRequestDto(String kinds, String nickname, long post_id) {
		this.kinds = kinds;
		this.nickname = nickname;
		this.post_id = post_id;
	} 
	
	public PostLikes toEntity() {
		return PostLikes.builder()
				.kinds(kinds)
				.post_id(post_id)
				.nickname(nickname)
				.build();
		}
	
	
	
	
}
