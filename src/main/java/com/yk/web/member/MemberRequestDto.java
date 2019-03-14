package com.yk.web.member;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
	private int num;
	
	@NotBlank(message="이메일을 작성해주세요.")
	@Email(message = "이메일 양식을 지켜주세요.")
	private String email;
	
	@NotBlank(message="닉네임을 작성해주세요.")
	private String nickname;
	
	@NotBlank(message="비밀번호를 작성해주세요.")
	private String password;
	
	@NotBlank(message="비밀번호를 다시 한번 작성해주세요.")
	private String comfirmPassword;
	
	@NotBlank(message = "전화번호를 작성해주세요.")
	@Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
	private String phoneNumber;
	
	@Builder
	public MemberRequestDto(String email, String nickname, String password, String phoneNumber) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	public Members toEntity() {
		return Members.builder()
				.email(email)
				.nickname(nickname)
				.password(password)
				.phoneNumber(phoneNumber)
				.build();
	}
	
}
