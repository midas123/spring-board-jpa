package com.yk.web.post.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yk.web.user.entity.BaseTimeEntity;
import com.yk.web.user.entity.Users;

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
	
	
	//@JsonIgnore
	@JsonManagedReference 
	@OneToMany(mappedBy = "post",
	        cascade = CascadeType.ALL, fetch = FetchType.EAGER,
	        orphanRemoval = true)
	private List<PostLikes> postLikes;
	
	@Builder
	public Posts(String nickname, String p_title, String p_content, long p_counts) {
		this.nickname = nickname;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_counts = p_counts;
	}
	
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	
}
