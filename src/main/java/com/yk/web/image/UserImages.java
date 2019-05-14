package com.yk.web.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.yk.web.user.entity.Users;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class UserImages {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long image_id;
	
	@Column(length=20, nullable=false)
	private String file_type;
	
	@Column(length=100, nullable=false)
	private String file_origin_name;
	
	@Column(length=100, nullable=false)
	private String file_save_name;
	
	@Lob
	private byte[] file_data;
	
	@OneToOne
	@JoinColumn(name="userid")
	private Users users_images;
	
	public UserImages() {
		
	}

	@Builder
	public UserImages(long image_id, String file_type, String file_origin_name, String file_save_name, byte[] file_data, Users users_images) {
		this.image_id = image_id;
		this.file_type = file_type;
		this.file_origin_name = file_origin_name;
		this.file_save_name = file_save_name;
		this.file_data = file_data;
		this.users_images = users_images;
	}
	
	
}
