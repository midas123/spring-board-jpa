package com.yk.web.post.entity;

import javax.persistence.*;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yk.web.user.entity.Users;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class PostLikes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long like_id;
	
	@Column
	private long likes;
	
	@Column
	private String kinds;
	
	@Column 
	private String nickname;

	@JsonBackReference 
	@ManyToOne
	@JoinColumn(name="post_id", referencedColumnName="post_id")
	private Posts post;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="com_id", referencedColumnName="com_id")
	private PostComments comment; 

	
	@Builder
	public PostLikes(long like_id, long likes, String kinds, String nickname, Posts post, PostComments comment) {
		this.like_id = like_id;
		this.likes = likes;
		this.kinds = kinds;
		this.nickname = nickname;
		this.post = post;
		this.comment = comment;
	}

}
