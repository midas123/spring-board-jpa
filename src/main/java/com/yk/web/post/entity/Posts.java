package com.yk.web.post.entity;

import java.util.ArrayList;
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
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<PostLikes> postLikes = new ArrayList<>();

	@JsonManagedReference 
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	@OrderBy("com_group_seq asc, com_re_seq asc")
	private List<PostComments> comments = new ArrayList<>();
	
	@Builder
	public Posts(long post_id, String nickname, String p_title, String p_content, long p_counts,
			List<PostLikes> postLikes, List<PostComments> comments) {
		this.post_id = post_id;
		this.nickname = nickname;
		this.p_title = p_title;
		this.p_content = p_content;
		this.p_counts = p_counts;
		this.postLikes = postLikes;
		this.comments = comments;
	}
	
	public Posts(long post_id) {
		this.post_id = post_id;
	}
	
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	
	public static List<PostLikes> addAllLikes(List<PostLikes> postLikes, String type){
		PostLikes ps = new PostLikes();
		List<PostLikes> pslist = new ArrayList<>();
		long total =0;
		long like_id = 0;
		for(int i=0; i<postLikes.size(); i++) {
			like_id = postLikes.get(i).getLike_id();
			long num = postLikes.get(i).getLikes();
			total = total + num;
		}
		ps.setLike_id(like_id);
		ps.setLikes(total);
		ps.setKinds(type);
		pslist.add(ps);
		return pslist;
	}
	
	public void addPostLikes(PostLikes postLike) {
		postLikes.add(postLike);
		postLike.setPost(this);
	}
		
	public void removeComment(PostLikes postLike) {
		postLikes.remove(postLike);
		postLike.setPost(null);
	}
	
	public void setPostLikes(List<PostLikes> postLikes) {
		this.postLikes = postLikes; 
	}
	
	public void setComments(List<PostComments> comments) {
		this.comments = comments;
	}
	
	public PostResponseDto toConvertPostResponseDto() {
		PostResponseDto dto = new PostResponseDto();
		//setter 메서드
		return dto;
	}
	
}
