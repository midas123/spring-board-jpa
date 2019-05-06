package com.yk.web.post.dto;

import javax.validation.constraints.NotBlank;

import com.yk.web.post.entity.PostComments;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostCommentRequestDto {
	private long com_id;
	@NotBlank
	private String com_content;
	@NotBlank
	private String com_nickname;
	
	private long com_likes;
	
	private String com_re_name;
	
	private long com_re_seq;
	
	private long post_id;
	
	@Builder
	public PostCommentRequestDto(long com_id, String com_content, String com_nickname, long com_likes,
			String com_re_name, long com_re_seq, long post_id) {
		this.com_id = com_id;
		this.com_content = com_content;
		this.com_nickname = com_nickname;
		this.com_likes = com_likes;
		this.com_re_name = com_re_name;
		this.com_re_seq = com_re_seq;
		this.post_id = post_id;
	}
	
	
	public PostComments toEntity() {
		return PostComments.builder()
				.com_id(com_id)
				.com_likes(com_likes)
				.com_content(com_content)
				.com_re_name(com_re_name)
				.com_nickname(com_nickname)
				.com_re_seq(com_re_seq)
				.build();
	}
	
	
	
}
