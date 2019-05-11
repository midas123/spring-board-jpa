package com.yk.web.post.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yk.web.user.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Getter
@ToString
public class PostComments extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long com_id;
	
	@Column
	private String com_content;
	
	@Column
	private String com_nickname;
	
	@Column
	private String com_re_name;
	
	@Column
	private long com_re_seq;
	
	@Column
	private long com_depth;
	
	@Column
	private long com_group_seq;
	
	@Column
	private boolean isDeleted;
	
	@JsonManagedReference
	@OneToMany(mappedBy="comment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	List<PostLikes> postLikes;
	
	public void setPostLikes(List<PostLikes> postLikes) {
		this.postLikes = postLikes;
	}
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="post_id", referencedColumnName="post_id")
	private Posts post;
	
	public void setPosts(Posts post) {
		this.post = post;
	}
	
	public PostComments(long com_id) {
		this.com_id = com_id;
	}
	
	
	@Builder
	public PostComments(long com_id, String com_content, String com_nickname, long com_likes, String com_re_name,
			long com_re_seq, long com_depth, long com_group_seq, boolean isDeleted, List<PostLikes> postLikes, Posts post) {
		this.com_id = com_id;
		this.com_content = com_content;
		this.com_nickname = com_nickname;
		this.com_re_name = com_re_name;
		this.com_re_seq = com_re_seq;
		this.com_depth = com_depth;
		this.com_group_seq = com_group_seq;
		this.isDeleted = isDeleted;
		this.postLikes = postLikes;
		this.post = post;
	}
	
	
}
