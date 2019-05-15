package com.yk.web.image;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.yk.web.user.entity.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserImagesRequestDto {
	private long image_id;
	
	private String file_type;
	
	private String file_origin_name;
	
	private String file_save_name;
	
	private Users users_images;
	
	//private byte[] file_data;
	
	private MultipartFile file_data;
	
	@Builder
	public UserImagesRequestDto(long image_id, String file_type, String file_origin_name, String file_save_name,
			Users users_images, MultipartFile file_data, Users user) {
		this.image_id = image_id;
		this.file_type = file_type;
		this.file_origin_name = file_origin_name;
		this.file_save_name = file_save_name;
		this.users_images = users_images;
		this.file_data = file_data;
	}
	
	public UserImages toEntity() throws IOException {
		return UserImages.builder()
				.file_type(file_type)
				.file_origin_name(file_origin_name)
				.file_save_name(file_save_name)
				.users_images(users_images)
				.image_id(image_id)
				.users_images(users_images)
				.build();
	}

}
