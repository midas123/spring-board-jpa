package com.yk.web.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long post_id;
	
	@Column
	private String nickname;
	
	@Column(length=100)
	private String p_title;
	
	@Column
	private String p_content;
	
	/*@Column
	private int b_like;*/
	
	@Column
	private long p_counts;

	@Column
	private boolean p_deleted = false;
	
	@Column
	private boolean p_blinded = false;
	
	
	@Builder
	public Posts(String nickname, String p_title, String p_content, long p_counts) {
		this.nickname = nickname;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_counts = p_counts;
	}
	
}
