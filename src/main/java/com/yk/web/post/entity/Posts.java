package com.yk.web.post.entity;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yk.web.post.dto.PostResponseDto;
import com.yk.web.user.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
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
	
	//@JsonIgnore
	@JsonManagedReference 
	@OneToMany(mappedBy = "post",
	        cascade = CascadeType.ALL, fetch = FetchType.EAGER
	       )
	private List<PostLikes> postLikes;

	
	@JsonManagedReference 
	@OneToMany(mappedBy = "post2")
	private List<PostComments> postComments;
	
	@Builder
	public Posts(long post_id, String nickname, String p_title, String p_content, long p_counts,
			List<PostLikes> postLikes, List<PostComments> postComments) {
		this.post_id = post_id;
		this.nickname = nickname;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_counts = p_counts;
		this.postLikes = postLikes;
		this.postComments = postComments;
	}
	
	public Posts(long post_id) {
		this.post_id = post_id;
	}
	
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	
	
	public PostResponseDto toConvertPostResponseDto() {
		PostResponseDto dto = new PostResponseDto();
		//setter 메서드
		return dto;
	}

	
	
}
