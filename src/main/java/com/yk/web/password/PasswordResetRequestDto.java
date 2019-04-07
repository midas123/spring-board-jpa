package com.yk.web.password;

import javax.validation.constraints.NotBlank;

public class PasswordResetRequestDto {

	@NotBlank(message="이메일을 작성해주세요.")
    private String email;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
