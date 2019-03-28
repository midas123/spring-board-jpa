package com.yk.web.user;

import lombok.Builder;

public class UserResponseDto {
	private String username;
	
	private String nickname;
	
	private String password;
	
	private String phoneNumber;
	
	@Builder
	public UserResponseDto(String email, String nickname, String password, String phoneNumber) {
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
	
}
