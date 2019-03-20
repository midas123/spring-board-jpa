package com.yk.web.member;

import lombok.Builder;

public class MemberResponseDto {
	private String email;
	
	private String nickname;
	
	private String password;
	
	private String phoneNumber;
	
	@Builder
	public MemberResponseDto(String email, String nickname, String password, String phoneNumber) {
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
