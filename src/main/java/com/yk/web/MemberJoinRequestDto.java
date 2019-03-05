package com.yk.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberJoinRequestDto {
	private int num;
	private String email;
	private String nickname;
	private String password;
	
	public Members toEntity() {
		return Members.builder()
				.email(email)
				.nickname(nickname)
				.password(password)
				.build();
	}
}
