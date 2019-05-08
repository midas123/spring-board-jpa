package com.yk.web.post.dto;

import javax.validation.constraints.NotBlank;

import com.yk.web.post.entity.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
	private long post_id;
	
	@NotBlank
	private String nickname;
	
	@NotBlank
	private String p_title;
	
	@NotBlank
	private String p_content;

	private long p_counts;
	
	private long likes;
	
/*	@Builder
	public PostRequestDto(String nickname, String p_title, String p_content, long p_counts, long likes) {
		this.nickname = nickname;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_counts = p_counts;
		this.likes = likes;
	}*/
	
	public Posts toEntity() {
		return Posts.builder()
				.nickname(nickname)
				.p_content(p_content)
				.p_title(p_title)
				.p_counts(p_counts)
				.build();
	}
	
}
