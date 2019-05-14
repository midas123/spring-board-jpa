package com.yk.web.user.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.yk.web.image.UserImagesRequestDto;
import com.yk.web.user.entity.Users;
import com.yk.web.user.valid.EmailValid;
import com.yk.web.user.valid.PasswordMatch;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@PasswordMatch.List({ 
	@PasswordMatch(field = "password", fieldMatch = "confirmPassword", message = "비밀번호가 서로 다릅니다."), 
})
public class UserRequestDto {
	@NotBlank(message="이메일을 작성해주세요.")
	//@Email(message = "이메일 양식을 지켜주세요.")
	@EmailValid
	private String username;
	
	@NotBlank(message="닉네임을 작성해주세요.")
	private String nickname;
	
	@NotBlank(message="비밀번호를 작성해주세요.")
	private String password;
	
	@NotBlank(message="비밀번호를 다시 한번 작성해주세요.")
	private String confirmPassword;
	
	@NotBlank(message = "전화번호를 작성해주세요.")
	@Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
	private String phoneNumber;
	
	private String file_type;
	
	private String file_origin_name;
	
	private String file_save_name;
	
	private byte[] file_data;
	
	
	@Builder
	public UserRequestDto(String email, String nickname, String password, String phoneNumber) {
		this.username = email;
		this.nickname = nickname;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	public Users toEntity() {
		return Users.builder()
				.username(username)
				.nickname(nickname)
				.password(password)
				.phoneNumber(phoneNumber)
				.build();
	}
	
	public UserImagesRequestDto toConvertUserImagesRequestDto() {
		return UserImagesRequestDto.builder()
				.file_type(file_type)
				.file_origin_name(file_origin_name)
				.file_save_name(file_save_name)
				.file_data(file_data)
				.build();
	}
	
}
