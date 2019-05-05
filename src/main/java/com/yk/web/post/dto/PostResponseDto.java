package com.yk.web.post.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostResponseDto {
	private String nickname;
	
	private String p_title;
	
	private String p_content;
	
	private long p_counts;
	
	private long likes;
	
	private boolean p_deleted;

	private boolean p_blinded;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
    
	
	
}
