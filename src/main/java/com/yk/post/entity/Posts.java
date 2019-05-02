package com.yk.post.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.yk.user.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long post_id;
	
	@Column
	private String nickname;
	
	@Column(length=100)
	private String p_title;
	
	@Column
	private String p_content;
	
	@Column
	private long p_counts;

	@Column
	private boolean p_deleted = false;
	
	@Column
	private boolean p_blinded = false;
	
	@OneToMany(mappedBy="post")
	private List<PostLikes> postLikes;
	
	@Builder
	public Posts(String nickname, String p_title, String p_content, long p_counts) {
		this.nickname = nickname;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_counts = p_counts;
	}
	
}
