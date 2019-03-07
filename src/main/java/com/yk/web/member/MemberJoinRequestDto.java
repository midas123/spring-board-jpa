package com.yk.web.member;

import lombok.Builder;
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
	
	@Builder
	public MemberJoinRequestDto(String email, String nickname, String password) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}
	
	public Members toEntity() {
		return Members.builder()
				.email(email)
				.nickname(nickname)
				.password(password)
				.build();
	}
	
	
}
