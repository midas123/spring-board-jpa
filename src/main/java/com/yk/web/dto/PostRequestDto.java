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
	private long post_id;
	
	@NotBlank
	private String nickname;
	@NotBlank
	private String p_title;
	@NotBlank
	private String p_content;
	
	/*@NotBlank
	private int b_like;*/
	
	@NotBlank
	private long p_counts;
	
	private boolean p_deleted;
	
	private boolean p_blinded;
	
	@Builder
	public PostRequestDto(String nickname, String p_title, String p_content, long p_counts) {
		this.nickname = nickname;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_counts = p_counts;
	}
	
	public Posts toEntity() {
		return Posts.builder()
				.nickname(nickname)
				.p_content(p_content)
				.p_title(p_title)
				.p_counts(p_counts)
				.build();
	}

}
