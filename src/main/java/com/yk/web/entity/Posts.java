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
	private int board_id;
	
	@Column
	private String nickname;
	
	@Column(length=100)
	private String b_title;
	
	@Column
	private String b_content;
	
	@Column
	private int b_like;
	
	@Column
	private int b_counts;

/*	@Column
	private boolean b_deleted = false;*/
	
	
	@Builder
	public Posts(String nickname, String b_title, String b_content, int b_like, int b_counts) {
		this.nickname = nickname;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_like = b_like;
		this.b_counts = b_counts;
	}
	
}
