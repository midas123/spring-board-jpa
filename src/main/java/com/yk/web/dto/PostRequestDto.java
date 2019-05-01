package com.yk.web.dto;

import javax.validation.constraints.NotBlank;
import com.yk.web.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
	@NotBlank
	private String nickname;
	@NotBlank
	private String b_title;
	@NotBlank
	private String b_content;
	
	@NotBlank
	private int b_like;
	@NotBlank
	private int b_counts;
	
	//private boolean b_deleted;
	
	@Builder
	public PostRequestDto(String nickname, String b_title, String b_content, int b_like, int b_counts) {
		this.nickname = nickname;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_like = b_like;
		this.b_counts = b_counts;
	}
	
	public Posts toEntity() {
		return Posts.builder()
				.nickname(nickname)
				.b_content(b_content)
				.b_title(b_title)
				.b_like(b_like)
				.b_counts(b_counts)
				.build();
	}
	
	@Override
	public String toString() {
		
		return nickname + b_title + b_content + b_like + b_counts;
	}
}
