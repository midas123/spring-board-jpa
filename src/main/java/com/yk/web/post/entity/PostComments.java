package com.yk.web.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Getter
@ToString
public class PostComments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long com_id;
	
	@Column
	private String com_content;
	
	@Column
	private String com_nickname;
	
	@Column
	private long com_likes;
	
	@Column
	private String com_re_name;
	
	@Column
	private long com_re_seq;
	
	/*@Transient
	private long post_id;*/
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="post_id", referencedColumnName="post_id")
	private Posts post2;
	
	@Builder
	public PostComments(long com_id, String com_content, String com_nickname, long com_likes, String com_re_name,
			long com_re_seq) {
		this.com_id = com_id;
		this.com_content = com_content;
		this.com_nickname = com_nickname;
		this.com_likes = com_likes;
		this.com_re_name = com_re_name;
		this.com_re_seq = com_re_seq;
	}
	
	
}
