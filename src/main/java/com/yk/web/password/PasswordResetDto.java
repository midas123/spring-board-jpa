package com.yk.web.password;

import javax.validation.constraints.NotBlank;

import com.yk.web.user.valid.PasswordMatch;


@PasswordMatch.List({ 
	@PasswordMatch(field = "password", fieldMatch = "confirmpassword", message = "비밀번호가 서로 다릅니다."), 
})
public class PasswordResetDto {

	@NotBlank(message="비밀번호를 작성해주세요.")
    private String password;

    private String confirmpassword;

    @NotBlank
    private String token;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
  
}
